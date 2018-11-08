package com.example.tj.qq.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tj.qq.R;

public class DrawerLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout mainLL;
    private LinearLayout leftLL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        initView();
        initListener();

    }

    private void initView() {
        drawerLayout = findViewById(R.id.adl_drawer_layout);
        mainLL = findViewById(R.id.ll_main);
        leftLL = findViewById(R.id.ll_left);

    }

    private void initListener() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);// slideOffset  0-1

//                float scale = 1 - slideOffset;// 1-0
//                float rightScale = 0.8f + scale * 0.2f;// 1-0.8
//                float leftScale = 1 - 0.3f * scale;// 0.7-1
//
//                leftLL.setScaleX(leftScale);
//                leftLL.setScaleY(leftScale);
//                leftLL.setAlpha(0.6f + 0.4f * (1 - scale));
//
//                mainLL.setScaleX(rightScale);
//                mainLL.setScaleY(rightScale);
//                mainLL.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));


            }
        });
    }


}
