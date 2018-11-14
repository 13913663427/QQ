package com.example.tj.qq.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tj.qq.R;
import com.example.tj.qq.fragment.LeftFragment;
import com.example.tj.qq.fragment.MainFragment;

public class DrawerLayoutActivity extends BaseActivity {

    private DrawerLayout drawerLayout;
    private FrameLayout leftMenu;
    private FrameLayout mainMenu;
    public LeftFragment leftFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    protected void initView() {
        leftMenu = findViewById(R.id.fl_left);
        mainMenu = findViewById(R.id.fl_main);
        drawerLayout = findViewById(R.id.adl_drawer_layout);

        leftFragment = new LeftFragment();

        getSupportFragmentManager()//
                .beginTransaction()//
                .add(R.id.fl_main, new MainFragment())//
                .add(R.id.fl_left, leftFragment)//
                .commit();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        // QQ5.0特效
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                float leftScale = 0.6f + 0.4f * slideOffset;
                leftMenu.setScaleX(leftScale);
                leftMenu.setScaleY(leftScale);
                leftMenu.setAlpha(leftScale);

                float mainScale = 1 - 0.4f * slideOffset;
                mainMenu.setScaleX(mainScale);
                mainMenu.setScaleY(mainScale);
                mainMenu.setTranslationX(drawerView.getMeasuredWidth() * slideOffset * 0.7f);
            }
        });
    }


    public void openDrawer() {
        drawerLayout.openDrawer(leftMenu);
    }
}
