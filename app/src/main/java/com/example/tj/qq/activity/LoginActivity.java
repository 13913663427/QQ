package com.example.tj.qq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tj.qq.R;
import com.example.tj.qq.api.Api;
import com.example.tj.qq.api.UserInterface;
import com.example.tj.qq.item.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

            Retrofit retrofit = Api.getRetrofit();
            UserInterface userInterface = retrofit.create(UserInterface.class);
            Call<User> call = userInterface.login(userName, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });

        });

    }
}

