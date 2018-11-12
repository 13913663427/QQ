package com.example.tj.qq.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tj.qq.R;

public class MainFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = View.inflate(getActivity(), R.layout.fragment_main, null);
        initView();
        return rootView;
    }

    private void initView() {
        TextView textView = rootView.findViewById(R.id.tv_test);
    }
}
