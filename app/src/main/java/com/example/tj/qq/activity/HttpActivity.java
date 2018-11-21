package com.example.tj.qq.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.tj.qq.R;
import com.example.tj.qq.item.UserItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpActivity extends BaseActivity implements View.OnClickListener {

    private EditText nameET;
    private EditText pwdET;
    private Button registerBTN;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_http;
    }

    @Override
    protected void initView() {
        nameET = findViewById(R.id.et_name);
        pwdET = findViewById(R.id.et_pwd);
        registerBTN = findViewById(R.id.btn_register);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        registerBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                doRegister();
                break;
        }
    }

    private void doRegister() {
        String userName = nameET.getText().toString().trim();
        String password = pwdET.getText().toString().trim();
        UserItem userItem = new UserItem();
        userItem.setUserName(userName);
        userItem.setPassword(password);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON.toJSONString(userItem));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        OkGo.<String>post("http://139.199.14.212:10086/user/register").upJson(jsonObject).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String backStr = response.body();
                com.alibaba.fastjson.JSONObject jo = JSON.parseObject(backStr);
                if (jo.getIntValue("code") == 200) {
                    // 注册成功
                    UserItem user = JSON.parseObject(JSON.toJSONString(jo.get("data")), UserItem.class);
                    // 做相关逻辑操作

                }
                Toast.makeText(HttpActivity.this, jo.getString("message"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
