package com.example.tj.qq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tj.qq.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private String phone_number;
    private String code_number;

    private View backTV;
    private View nextBTN;
    private EditText edit_phone;
    private EditText editCODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);



        initListener();
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        backTV = findViewById(R.id.text_back);
        nextBTN = findViewById(R.id.btn_code);
        editCODE = findViewById(R.id.edit_code);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        backTV.setOnClickListener(this);
        nextBTN.setOnClickListener(this);
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

                            boolean smart = (Boolean)data;
                            if(smart) {
                                Toast.makeText(getApplicationContext(),"该手机号已经注册过，请重新输入",
                                        Toast.LENGTH_LONG).show();
                                edit_phone.requestFocus();
//                                return;
                            }


                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            Toast.makeText(getApplicationContext(), "验证码输入正确",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data).printStackTrace();
                            Toast.makeText(getApplicationContext(),"验证码输入错误", Toast.LENGTH_LONG).show();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }
            }).sendMessage(msg);
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_back:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_code:
                // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", phone_number);
                editCODE.requestFocus();
                break;
            case R.id.btn_next:
                SMSSDK.submitVerificationCode("86",phone_number,code_number);
                break;
            default:
                break;
        }
    }

    private boolean judPhone()
    {
        if(TextUtils.isEmpty(edit_phone.getText().toString().trim()))
        {
            Toast.makeText(RegisterActivity.this,"请输入您的电话号码",Toast.LENGTH_LONG).show();
            edit_phone.requestFocus();
            return false;
        }
        else if(edit_phone.getText().toString().trim().length()!=11)
        {
            Toast.makeText(RegisterActivity.this,"您的电话号码位数不正确",Toast.LENGTH_LONG).show();
            edit_phone.requestFocus();
            return false;
        }
        else
        {
            phone_number=edit_phone.getText().toString().trim();
            String num="[1][358]\\d{9}";
            if(phone_number.matches(num))
                return true;
            else
            {
                Toast.makeText(RegisterActivity.this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    private boolean judCord()
    {
        judPhone();
        if(TextUtils.isEmpty(editCODE.getText().toString().trim()))
        {
            Toast.makeText(RegisterActivity.this,"请输入您的验证码",Toast.LENGTH_LONG).show();
            editCODE.requestFocus();
            return false;
        }
        else if(editCODE.getText().toString().trim().length()!=4)
        {
            Toast.makeText(RegisterActivity.this,"您的验证码位数不正确",Toast.LENGTH_LONG).show();
            editCODE.requestFocus();

            return false;
        }
        else
        {
            code_number=editCODE.getText().toString().trim();
            return true;
        }

    }

    // 使用完EventHandler需注销，否则可能出现内存泄漏
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
