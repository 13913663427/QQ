package com.example.tj.qq.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tj.qq.R;
import com.example.tj.qq.item.UserItem;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ReadWriteActivity extends AppCompatActivity {
    private final String TAG = ReadWriteActivity.class.getSimpleName();
    private TextView contentTV;

    private String SDCardPath = Environment.getExternalStorageDirectory().getPath() + "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_write);
        final SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);  //生成一个config的xml私有文件

        // 代码动态申请权限
        RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(granted -> {
            if (granted) {
                writeFile();
            } else {
                Toast.makeText(this, "文件读写失败", Toast.LENGTH_SHORT).show();
            }
        });


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
                contentTV.setText(userName + "\n" + password + "\n");
            }
        });

    }

    private void writeFile() {
        File file = new File(SDCardPath, "user");
        if (!file.exists()) {
            file.mkdir();
        }


        try {
            File txtFile = new File(file.getPath(), "a.bmp");//在file文件夹路径下创建一个叫a.txt的文件
            FileOutputStream fos = new FileOutputStream(txtFile);//字节输出流写入到这个文件
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.bg_background);
            fos.write(Bitmap2Bytes(bmp));
//            fos.write("a大王叫我来巡山2".getBytes());     //字节输出流,将字符串转换成字节
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

}
