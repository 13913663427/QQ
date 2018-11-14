package com.example.tj.qq.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tj.qq.R;
import com.example.tj.qq.activity.DrawerLayoutActivity;

public class MessageFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.fragment_message, null);
        initView();
        return rootView;
    }

    private void initView() {
        rootView.findViewById(R.id.btn_open_drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayoutActivity drawerLayoutActivity = (DrawerLayoutActivity) getActivity();
                drawerLayoutActivity.openDrawer();

//                DrawerLayoutActivity drawerLayoutActivity = (DrawerLayoutActivity) getActivity();
//                drawerLayoutActivity.leftFragment.log();

            }
        });
    }
}
