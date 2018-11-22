package com.example.tj.qq.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class PhoneUtil {

    private EventHandler eventHandler;

    public static PhoneUtil getInstance() {
        return new PhoneUtil();
    }


    // 1 定义接口和方法
    public interface PhoneResultListener {
        void onSuccess(Object data);

        void onFailed(Object data);
    }

    // 2. 声明接口
    private PhoneResultListener resultListener;

    // 3. 实现set方法
    public void setResultListener(PhoneResultListener resultListener) {
        this.resultListener = resultListener;
    }

    private EventHandler getEventHandler() {
        return new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                new Handler(Looper.getMainLooper(), msg1 -> {
                    int event1 = msg1.arg1;
                    int result1 = msg1.arg2;
                    Object data1 = msg1.obj;
                    if (event1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result1 == SMSSDK.RESULT_COMPLETE) {
                            // 处理验证码验证通过的结果
                            if (resultListener != null) {
                                resultListener.onSuccess(data1);
                            }
                        } else {
                            //  处理错误的结果
                            if (resultListener != null) {
                                resultListener.onFailed(data1);
                            }
                        }
                    }
                    return false;
                }).sendMessage(msg);
            }
        };
    }

    public void onCreate() {
        eventHandler = getEventHandler();
        SMSSDK.registerEventHandler(eventHandler);
    }

    public void onDestroy() {
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}
