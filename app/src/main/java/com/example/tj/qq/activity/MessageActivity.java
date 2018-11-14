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

    private ArrayList<MessageItem> dataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
        initData();
        initListener();

    }

    private void initListener() {
    }

    private void initView(){
        //创建适配器
        adapter = new MessageAdapter(this, dataList);

        //将Item组成的列表填充到Recycleview里面(绑定视图)
        recyclerView = findViewById(R.id.recycler_view);

        //设置RecycleView布局文件显示策略，LinearLayout线性垂直方向显示
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        long timeStamp = 0;
        try {
            timeStamp = format.parse("2018-11-05 14:00:11").getTime();
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

        messageItem = new MessageItem("http://img0.imgtn.bdimg.com/it/u=2577811927,228535961&fm=26&gp=0.jpg",
                "大脚丫", "我的脚丫大吗?", timeStamp+123456);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://img1.imgtn.bdimg.com/it/u=658942913,2860438964&fm=11&gp=0.jpg",
                "树木", "我是一棵大树", timeStamp+234567);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://wx2.sinaimg.cn/large/94cea970ly1fn2gx5dx4aj20dw0dw0ub.jpg",
                "长桥", "好长的桥啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊你想知道的我都有", timeStamp+345678);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://tupian.qqjay.com/u/2017/1118/1_162252_2.jpg",
                "海滩", "晒个太阳爽歪歪", timeStamp+456324);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://img.daimg.com/uploads/allimg/120319/1-12031921534Y24.jpg",
                "大草原", "苍茫的天涯是我的爱，绵绵的青山脚下花正开", timeStamp+567890);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://img.daimg.com/uploads/allimg/111006/1-111006233019140.jpg",
                "美女", "我也不知道她是谁", timeStamp+678910);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://pic.58pic.com/58pic/12/81/58/78m58PICdgR.jpg",
                "大脚丫", "我的脚丫大吗?", timeStamp+789101);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://a3.topitme.com/9/92/ce/1126992283a83ce929l.jpg",
                "禅语", "菩提本无树，明镜亦非台，本来无一物，何处惹尘埃", timeStamp+7891011);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://tupian.qqjay.com/u/2017/1118/1_155119_1.jpg",
                "爱心湖", "牛逼啊，这个湖的形状", timeStamp+789101112);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://pic.58pic.com/58pic/15/36/60/00W58PICmGB_1024.jpg",
                "高清花纹", "花纹，图片，素材，下载为主", timeStamp);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://tupian.qqjay.com/u/2017/1208/3_143331_1.jpg",
                "高清花纹", "精心收集的图片,内容以高清，花纹，图片，素材，下载为主,精心收集的图片,内容以高清，花纹，图片，素材，下载为主,精心收集的图片,内容以高清，花纹，图片，素材，下载为主", timeStamp+10086);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://img0.imgtn.bdimg.com/it/u=2577811927,228535961&fm=26&gp=0.jpg",
                "大脚丫", "我的脚丫大吗?", timeStamp+123456);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://img1.imgtn.bdimg.com/it/u=658942913,2860438964&fm=11&gp=0.jpg",
                "树木", "我是一棵大树", timeStamp+234567);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://wx2.sinaimg.cn/large/94cea970ly1fn2gx5dx4aj20dw0dw0ub.jpg",
                "长桥", "好长的桥啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊你想知道的我都有", timeStamp+345678);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://tupian.qqjay.com/u/2017/1118/1_162252_2.jpg",
                "海滩", "晒个太阳爽歪歪", timeStamp+456324);
        dataList.add(messageItem);

        messageItem = new MessageItem("http://img.daimg.com/uploads/allimg/120319/1-12031921534Y24.jpg",
                "大草原", "苍茫的天涯是我的爱，绵绵的青山脚下花正开", timeStamp+567890);
        dataList.add(messageItem);

        recyclerView.setAdapter(adapter);
    }
}
