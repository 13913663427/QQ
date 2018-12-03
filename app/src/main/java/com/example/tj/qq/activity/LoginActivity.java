package com.example.tj.qq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tj.qq.R;
import com.example.tj.qq.contract.UserContract;
import com.example.tj.qq.item.User;
import com.example.tj.qq.item.User1;
import com.example.tj.qq.model.UserModel;
import com.example.tj.qq.presenter.UserPresenter;
import com.example.tj.qq.utils.ToastUtil;
import com.qzb.common.base.BaseActivity;

public class LoginActivity extends BaseActivity implements UserContract.View {
    private TextView regesiterTV;
    private EditText nameET;
    private EditText passwordET;
    private User user;
    private User1 user1;
    private UserPresenter userPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {
        userPresenter = (UserPresenter) getPresenter(UserPresenter.class, UserModel.class);

    }

    @Override
    public void initView() {
        regesiterTV = findViewById(R.id.text_regesiter);
        nameET = findViewById(R.id.et_user_name);
        passwordET = findViewById(R.id.et_password);
        initListener();
    }


    private void initListener() {
        regesiterTV.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });

        findViewById(R.id.btn_login).setOnClickListener(v -> {
            String userName = nameET.getText().toString().trim();
            String password = passwordET.getText().toString().trim();
            userPresenter.login(userName, password);

        });

    }

    @Override
    public void loginSuccess() {
        ToastUtil.toast(this, "登陆成功", Toast.LENGTH_SHORT);
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void showLoading(String s) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String s) {
        ToastUtil.toast(this, s, Toast.LENGTH_SHORT);

    }
}

