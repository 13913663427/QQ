package com.example.tj.qq.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tj.qq.R;
import com.example.tj.qq.utils.PhoneUtil;

import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView editPHONE;
    private TextView editCODE;
    PhoneUtil phoneUtil = PhoneUtil.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        phoneUtil.onCreate();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        editPHONE = findViewById(R.id.edit_phone);
        editCODE = findViewById(R.id.edit_code);
    }

    @Override
    protected void initData() {

    }

    protected void initListener() {
        findViewById(R.id.text_back).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_code).setOnClickListener(this);

        phoneUtil.setResultListener(new PhoneUtil.PhoneResultListener() {
            @Override
            public void onSuccess(Object data) {
                Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(Object data) {
                // {"@type":"java.lang.Throwable","localizedMessage":"{\"detail\":\"用户提交校验的验证码错误。\",\"description\":\"需要校验的验证码错误\",\"httpStatus\":400,\"status\":468,\"error\":\"The length of code should be 4\"}","message":"{\"detail\":\"用户提交校验的验证码错误。\",\"description\":\"需要校验的验证码错误\",\"httpStatus\":400,\"status\":468,\"error\":\"The length of code should be 4\"}","stackTrace":[{"className":"cn.smssdk.net.b","fileName":"Config.java","lineNumber":472,"methodName":"a","nativeMethod":false},{"className":"cn.smssdk.net.b","fileName":"Config.java","lineNumber":437,"methodName":"a","nativeMethod":false},{"className":"cn.smssdk.net.b","fileName":"Config.java","lineNumber":395,"methodName":"a","nativeMethod":false},{"className":"cn.smssdk.net.f","fileName":"Protocols.java","lineNumber":184,"methodName":"b","nativeMethod":false},{"className":"cn.smssdk.b","fileName":"SMSSDKCore.java","lineNumber":296,"methodName":"c","nativeMethod":false},{"className":"cn.smssdk.b","fileName":"SMSSDKCore.java","lineNumber":162,"methodName":"b","nativeMethod":false},{"className":"cn.smssdk.b","fileName":"SMSSDKCore.java","lineNumber":36,"methodName":"a","nativeMethod":false},{"className":"cn.smssdk.b$2","fileName":"SMSSDKCore.java","lineNumber":153,"methodName":"run","nativeMethod":false}],"suppressed":[]}
                ((Throwable) data).printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_back:
                finish();
                break;
            case R.id.btn_code:
                String phone_number = editPHONE.getText().toString().trim();
                // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", phone_number);
                break;
            case R.id.btn_next:
                String phone_code = editCODE.getText().toString().trim();
                phone_number = editPHONE.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", phone_number, phone_code);
        }
    }

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        phoneUtil.onDestroy();
    }
}
