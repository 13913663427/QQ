package com.example.tj.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class LoginActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);
//        // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
//        SMSSDK.getVerificationCode("86", "18251986292");
//        // 提交验证码，其中的code表示验证码，如“1357”
//        SMSSDK.submitVerificationCode("86", "18251986292", "");

        initListener();

    }

    private void initListener() {
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

    }


    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", "18251986292");
                break;
        }
    }

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
