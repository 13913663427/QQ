package com.example.tj.qq;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.example.tj.qq.activity.BaseActivity;
import com.example.tj.qq.item.UserItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class HttpActivity extends BaseActivity {

    private TextView baiduTV;
    private TextView contentTV;
    private ImageView headIV;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_http;
    }

    @Override
    protected void initView() {
        baiduTV = findViewById(R.id.ah_tv_baidu);
        contentTV = findViewById(R.id.ah_tv_content);
        headIV = findViewById(R.id.iv_head);


    }

    @Override
    protected void initData() {

        OkGo.<String>get("http:www.baidu.com").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.e(TAG, response.body());
                String html = response.body();
                baiduTV.setText(html);
            }
        });

        // 请求用户数据
        OkGo.<String>get("http://www.wanandroid.com/tools/mockapi/2452/getUser").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String jsonStr = response.body();
                UserItem userItem = JSON.parseObject(jsonStr, UserItem.class);//jsonArray转换成普通对象
                contentTV.setText(userItem.toString());
                Glide.with(HttpActivity.this).load(userItem.getHeadUrl()).into(headIV);
            }
        });

    }

    @Override
    protected void initListener() {

    }
}
