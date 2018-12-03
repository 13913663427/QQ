package com.example.tj.qq.model;

import com.example.tj.qq.api.Api;
import com.example.tj.qq.api.HostType;
import com.example.tj.qq.contract.UserContract;
import com.example.tj.qq.item.Message;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserModel implements UserContract.Model {
    @Override
    public Observable<Message> login(String name, String password) {
        return Api.getDefault(HostType.TYPE_TENCENT)//
                .login(name, password)//
                .subscribeOn(Schedulers.io())//
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Message> register(String name, String password) {
        return null;
    }
}
