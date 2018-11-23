package com.example.tj.qq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tj.qq.R;
import com.example.tj.qq.item.UserItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LoginActivity extends BaseActivity {
    private TextView regesiterTV;
    private EditText nameET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        regesiterTV = findViewById(R.id.text_regesiter);
        nameET = findViewById(R.id.et_user_name);
        passwordET = findViewById(R.id.et_password);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        regesiterTV.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });


        findViewById(R.id.btn_login).setOnClickListener(v -> {
            String userName = nameET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();
            OkGo.<String>post("http://139.199.14.212:10086/user/login")//
                    .params("userName", userName)//
                    .params("password", password)//
                    .execute(new StringCallback() {//
                        @Override
                        public void onSuccess(Response<String> response) {
                            String backstr = response.body();
                            JSONObject jsonObject = JSON.parseObject(backstr);

                            if (jsonObject.getIntValue("code") == 200) {
                                JSONObject data = jsonObject.getJSONObject("data");
                                UserItem userItem = JSON.parseObject(data.toJSONString(), UserItem.class);

                                Intent intent = new Intent(LoginActivity.this, DrawerLayoutActivity.class);
                                intent.putExtra("user", userItem);
                                startActivity(intent);
                            } else {
                                Toast toast = Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT);
                                LinearLayout linearLayout = (LinearLayout) toast.getView();
                                TextView messageTextView = (TextView) linearLayout.getChildAt(0);
                                messageTextView.setTextSize(60);
                                toast.show();
                            }


                        }
                    });
        });
    }

}
