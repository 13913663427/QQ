package com.example.tj.qq.utils;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {


    private static Toast toast;

    public static void toast(Context context, String content,int duration){
        toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        LinearLayout linearLayout = (LinearLayout) toast.getView();
        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
        messageTextView.setTextSize(60);
        toast.show();
    }
}
