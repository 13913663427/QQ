package com.example.tj.qq.contract;

import com.example.tj.qq.item.Message;
import com.qzb.common.base.BaseModel;
import com.qzb.common.base.BasePresenter;
import com.qzb.common.base.BaseView;

import io.reactivex.Observable;

public class UserContract {
    public interface Model extends BaseModel {
        Observable<Message> login(String name, String password);

        Observable<Message> register(String name, String password);
    }

    public interface View extends BaseView {
        void loginSuccess();

        void registerSuccess();
    }

    public static abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login(String name, String password);

        public abstract void register(String name, String password);
    }

}
