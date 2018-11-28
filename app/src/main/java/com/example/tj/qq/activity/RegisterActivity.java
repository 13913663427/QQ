package com.example.tj.qq.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tj.qq.R;
import com.example.tj.qq.utils.PhoneUtil;

import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private TextView editPHONE;
    private TextView editCODE;
    PhoneUtil phoneUtil = PhoneUtil.getInstance();
    private Button codeBTN;


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
        codeBTN = findViewById(R.id.btn_code);
    }

    @Override
    protected void initData() {

    }

    protected void initListener() {
        findViewById(R.id.text_back).setOnClickListener(this);
        findViewById(R.id.btn_next).setOnClickListener(this);
        codeBTN.setOnClickListener(this);

        phoneUtil.setResultListener(new PhoneUtil.PhoneResultListener() {
            @Override
            public void onSuccess(Object data) {
//                Intent intent = new Intent(RegisterActivity.this, RegisterAcountActivity.class);
//                startActivity(intent);

            }

            @Override
            public void onFailed(Object data) {

                Throwable throwable = (Throwable) data;
                String jsonStr = throwable.getMessage();
                JSONObject jo = JSON.parseObject(jsonStr);


                Toast toast = Toast.makeText(RegisterActivity.this, jo.getString("detail"), Toast.LENGTH_SHORT);
                LinearLayout linearLayout = (LinearLayout) toast.getView();
                TextView messageTextView = (TextView) linearLayout.getChildAt(0);
                messageTextView.setTextSize(60);
                toast.show();

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
                SMSSDK.getVerificationCode("86", phone_number);
                startTimer();
                break;
            case R.id.btn_next:
                String phone_code = editCODE.getText().toString().trim();
                phone_number = editPHONE.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", phone_number, phone_code);
        }
    }

    //倒计时
    private void startTimer() {
        codeBTN.setTextColor(getResources().getColor(R.color.btn_color_code_green));//
        codeBTN.setEnabled(false);
        new Thread() {
            @Override
            public void run() {
                for (int i = 59; i >= 0; i--) {
                    final int second = i;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (second <= 0) {
                                codeBTN.setTextColor(getResources().getColor(R.color.btn_get_verification_code));
                                codeBTN.setText(getResources().getString(R.string.reget_verification_code));
                                codeBTN.setEnabled(true);
                            } else {
                                codeBTN.setTextColor(getResources().getColor(R.color.btn_color_code_green));
                                codeBTN.setText(second + "s");
                            }
                        }
                    });
                }
            }
        }.start();
    }

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        phoneUtil.onDestroy();
    }
}
