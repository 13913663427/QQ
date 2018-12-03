package com.example.tj.qq.presenter;

import com.example.tj.qq.contract.UserContract;
import com.example.tj.qq.item.Message;
import com.example.tj.qq.utils.ErrorUtils;

import io.reactivex.functions.Consumer;

public class UserPresenter extends UserContract.Presenter {

    @Override
    public void login(String name, String password) {
        mRxManage.add(mModel.login(name, password).subscribe(new Consumer<Message>() {
            @Override
            public void accept(Message message) throws Exception {
                if (message.getCode() == 200) {
                    mView.loginSuccess();
                } else {
                    mView.showErrorTip(message.getMessage());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showErrorTip(ErrorUtils.getErrorMessage(throwable));

            }
        }));
    }

    @Override
    public void register(String name, String password) {

    }
}
