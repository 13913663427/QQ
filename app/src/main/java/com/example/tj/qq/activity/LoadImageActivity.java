package com.example.tj.qq.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.tj.qq.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadImageActivity extends Activity {

    private EditText urlET;
    private ImageView imageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);

        urlET = findViewById(R.id.et_image_url);
        imageIV = findViewById(R.id.iv_image);
        Glide.with(this)//
                .load("https://www.baidu.com/img/bd_logo1.png?where=super")//
                .apply(RequestOptions//
                        .bitmapTransform(new CircleCrop())//
                        .diskCacheStrategy(DiskCacheStrategy.NONE)//
                        .skipMemoryCache(true)//
                        .placeholder(R.mipmap.icon1)//
                        .error(R.mipmap.icon_girl))//
                .into(imageIV);

//        String url = "https://www.baidu.com/img/bd_logo1.png?where=super";
//        ImageLoderUtil.load(this,url,R.mipmap.icon1,R.mipmap.icon_girl,imageIV);


        findViewById(R.id.btn_load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 获取图片的url
                String url = urlET.getText().toString().trim();
                // 2. 根据url获取图片
//                loadImage(url);


            }
        });

    }

    private void loadImage(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 将String类型的url地址，转换成网络请求的Url
                    URL imageUrl = new URL(url);
                    // 根据url打开网络连接
                    HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                    // GET请求方法
                    conn.setRequestMethod("GET");
                    // 设置请求超时时间
                    conn.setConnectTimeout(5000);
                    // 判断是否请求成功，200就是OK，错误404，服务器本身问题500等
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        // 请求成功，获得字节流并且将字节流转换成Bitmap格式
                        InputStream in = conn.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(in);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 在主线程
                                imageIV.setImageBitmap(bitmap);
                            }
                        });

                    } else {
                        // 请求失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 在主线程
                                Toast.makeText(LoadImageActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 在主线程
                            Toast.makeText(LoadImageActivity.this, "请求失败", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }
}
