package com.example.tj.qq.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tj.qq.R;
import com.example.tj.qq.adapter.MessageAdapter;
import com.example.tj.qq.item.MessageItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MessageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //将显示的列表布局文件和Activity相关联
        setContentView(R.layout.activity_recycler_view);

        ArrayList<MessageItem> dataList = new ArrayList<>();


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        long timeStamp = 0;
        try {
            timeStamp = format.parse("2018-11-05 23:25:11").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //创建数据源

        MessageItem messageItem = new MessageItem("http://pic.58pic.com/58pic/15/36/60/00W58PICmGB_1024.jpg",
                "高清花纹", "花纹，图片，素材，下载为主", timeStamp);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://tupian.qqjay.com/u/2017/1208/3_143331_1.jpg",
                "高清花纹", "精心收集的图片,内容以高清，花纹，图片，素材，下载为主,精心收集的图片,内容以高清，花纹，图片，素材，下载为主,精心收集的图片,内容以高清，花纹，图片，素材，下载为主", timeStamp+10086);
        dataList.add(messageItem);

        MessageItem messageItem2 = new MessageItem("http://img0.imgtn.bdimg.com/it/u=2577811927,228535961&fm=26&gp=0.jpg",
                "大脚丫", "我的脚丫大吗?", timeStamp);
        dataList.add(messageItem2);

        //创建适配器
        MessageAdapter adapter = new MessageAdapter(this, dataList);

        //将Item组成的列表填充到Recycleview里面(绑定视图)
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //设置RecycleView布局文件显示策略，LinearLayout线性垂直方向显示
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}