package com.example.tj.qq.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tj.qq.R;


public class LeftFragment extends Fragment {

    private View leftview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        leftview = View.inflate(getActivity(), R.layout.activity_left,null);
        return leftview;

    }
}
