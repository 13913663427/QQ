package com.example.tj.qq.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tj.qq.R;
import com.example.tj.qq.api.Api;
import com.example.tj.qq.api.NetWorkConstants;
import com.example.tj.qq.api.UserRegisterInterface;
import com.example.tj.qq.item.User;
import com.example.tj.qq.item.User1;
import com.example.tj.qq.utils.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterAcountActivity extends BaseActivity implements View.OnClickListener {
    private EditText nameET;
    private EditText pwdET;
    private Button registerBTN;

    @Override
    protected int getLayoutId() {
        return R.layout.register_account;
    }

    @Override
    protected void initView() {
        nameET = findViewById(R.id.edit_name);
        pwdET = findViewById(R.id.edit_password);
        registerBTN = findViewById(R.id.btn_register);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        findViewById(R.id.text_back).setOnClickListener(this);
        registerBTN.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_back:
                finish();
                break;
            case R.id.btn_register:
                doRegister();
        }
    }

    private void doRegister() {
        String userName = nameET.getText().toString().trim();
        String password = pwdET.getText().toString().trim();
        User1 user1 = new User1();
        user1.setUserName(userName);
        user1.setPassword(password);

        Log.e(TAG, user1.hashCode() + "");
        Log.e(TAG, new User1().hashCode() + "");


        Retrofit retrofit = Api.getRetrofit(NetWorkConstants.BASE_URL);
        UserRegisterInterface userRegisterInterface = retrofit.create(UserRegisterInterface.class);
        Call<User> call = userRegisterInterface.register(user1);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user.getCode() == 200) {
                    startActivity(new Intent(RegisterAcountActivity.this, LoginActivity.class));
                } else if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    ToastUtil.toast(RegisterAcountActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT);
                } else {
                    ToastUtil.toast(RegisterAcountActivity.this, user.getMessage(), Toast.LENGTH_SHORT);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
