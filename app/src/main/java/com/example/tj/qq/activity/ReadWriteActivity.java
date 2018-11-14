package com.example.tj.qq.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.example.tj.qq.R;
import com.example.tj.qq.item.UserItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ReadWriteActivity extends Activity {

    private TextView contentTV;
    private String SDCardPath = Environment.getExternalStorageDirectory().getPath() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write);
        final SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);  //生成一个config的xml私有文件


        File file = new File(SDCardPath, "user");
        if (!file.exists()) {
            file.mkdir();
        }

        try {
            File txtFile = new File(file.getPath(), "a.txt");
            FileOutputStream fos = new FileOutputStream(txtFile);
//            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_background);
//            fos.write(Bitmap2Bytes(bmp));
            fos.write("a大王叫我来巡山".getBytes());     //字节输出流,将字符串转换成字节
            fos.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        contentTV = findViewById(R.id.tv_content);

        findViewById(R.id.btn_write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserItem userItem = new UserItem("張三", "123456");
                sp.edit()//将信息写入文件
                        .putString("userName", userItem.getUserName())//
                        .putString("password", userItem.getPassword())//
                        .apply();//提交

            }
        });

        findViewById(R.id.btn_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = sp.getString("userName", "李四");
                String password = sp.getString("password", "000000");
                String defaultStr = sp.getString("defaultStr", "111111");
                contentTV.setText(userName + "\n" + password + "\n" + defaultStr);

            }
        });

    }


    /**
     * 把Bitmap转Byte
     *
     * @Author HEH
     * @EditTime 2010-07-19 上午11:45:56
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

}
