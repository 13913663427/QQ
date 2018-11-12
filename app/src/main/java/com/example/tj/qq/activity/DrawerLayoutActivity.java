package com.example.tj.qq.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tj.qq.R;
import com.example.tj.qq.fragment.LeftFragment;
import com.example.tj.qq.fragment.MainFragment;

public class DrawerLayoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        getSupportFragmentManager()//
                .beginTransaction()//
                .add(R.id.fl_main, new MainFragment())//
                .add(R.id.fl_left, new LeftFragment())//
                .commit();
    }
}
