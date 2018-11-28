package com.example.tj.qq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tj.qq.R;
import com.example.tj.qq.api.Api;
import com.example.tj.qq.api.UserInterface;
import com.example.tj.qq.item.User;
import com.example.tj.qq.item.User1;

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

            Retrofit retrofit = Api.getRetrofit();
            UserInterface userInterface = retrofit.create(UserInterface.class);
            Call<User> call = userInterface.login(userName, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    user = response.body();
                    user1 = user.getData();
                    if (user.getCode() == 200) {
                        startActivity(new Intent(LoginActivity.this, DrawerLayoutActivity.class));
                    }
                    else{
                        Toast toast = Toast.makeText(LoginActivity.this, user.getMessage(), Toast.LENGTH_SHORT);
                        LinearLayout linearLayout = (LinearLayout) toast.getView();
                        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
                        messageTextView.setTextSize(60);
                        toast.show();

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                    Log.e(TAG,t.toString());
                    if (t instanceof ConnectException){
                        Toast toast = Toast.makeText(LoginActivity.this, "网络失去连接，请重新登陆", Toast.LENGTH_SHORT);
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

