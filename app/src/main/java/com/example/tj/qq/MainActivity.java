package com.example.tj.qq;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private LinearLayout leftMenu;
    private LinearLayout mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                leftMenu.setScaleX(0.2f + slideOffset);
                leftMenu.setScaleY(0.2f + slideOffset);
                leftMenu.setAlpha(0.6f + 0.4f * slideOffset);

                mainMenu.setScaleX(1 - 0.4f * slideOffset);
                mainMenu.setScaleY(1 - 0.4f * slideOffset);
                mainMenu.setTranslationX(drawerView.getMeasuredWidth() * slideOffset * 0.7f);
            }
        });
    }

    private void initView() {
        leftMenu = findViewById(R.id.left_menu);
        mainMenu = findViewById(R.id.main_menu);
        drawerLayout = findViewById(R.id.am_drawer_layout);
    }
}
