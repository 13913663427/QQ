package com.example.tj.qq.activity;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.tj.qq.R;
import com.example.tj.qq.item.UserItem;

import java.util.ArrayList;
import java.util.List;

public class JSONActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        try {
            List<UserItem> list = new ArrayList<>();
            UserItem userItem = new UserItem("张三", "123456");
            list.add(userItem);
            userItem = new UserItem("张三1", "123456");
            list.add(userItem);
            userItem = new UserItem("张三2", "123456");
            list.add(userItem);
            JSONArray ja = JSON.parseArray(JSON.toJSONString(list));
            String backStr = ja.toJSONString();//jsonarray转成jsonstring

            List<UserItem> users = JSON.parseArray(backStr, UserItem.class);


            Log.e(TAG, users.toString());
            Log.e(TAG, backStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {

    }
}
