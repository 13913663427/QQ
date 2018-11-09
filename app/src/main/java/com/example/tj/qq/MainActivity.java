package com.example.tj.qq;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tj.qq.fragment.LeftMenuFragment;
import com.example.tj.qq.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private FrameLayout leftMenu;
    private FrameLayout mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        leftMenu = findViewById(R.id.fl_left_menu);
        mainMenu = findViewById(R.id.fl_main);
        drawerLayout = findViewById(R.id.am_drawer_layout);

        // 初始化Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_left_menu, new LeftMenuFragment())
                .add(R.id.fl_main, new MainFragment())
                .commit();
    }

    private void initListener() {

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
}
