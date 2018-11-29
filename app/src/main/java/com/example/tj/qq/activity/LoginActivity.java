package com.example.tj.qq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tj.qq.R;
import com.example.tj.qq.api.Api;
import com.example.tj.qq.api.NetWorkConstants;
import com.example.tj.qq.api.UserLoginInterface;
import com.example.tj.qq.item.User;
import com.example.tj.qq.item.User1;
import com.example.tj.qq.utils.ToastUtil;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends BaseActivity {
    private TextView regesiterTV;
    private EditText nameET;
    private EditText passwordET;
    private User user;
    private User1 user1;


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

            Retrofit retrofit = Api.getRetrofit(NetWorkConstants.BASE_URL);
            UserLoginInterface userLoginInterface = retrofit.create(UserLoginInterface.class);
            Call<User> call = userLoginInterface.login(userName, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    user = response.body();
                    user1 = user.getData();
                    if (user.getCode() == 200) {
                        startActivity(new Intent(LoginActivity.this, DrawerLayoutActivity.class));
                    }
                    else{
                        ToastUtil.toast(LoginActivity.this,user.getMessage(),Toast.LENGTH_SHORT);


                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    Log.e(TAG,t.toString());
                    if (t instanceof ConnectException){
                       ToastUtil.toast(LoginActivity.this,"网络失去连接，请检查网络",Toast.LENGTH_SHORT);
                    }
                }

            });

        });

    }
}

