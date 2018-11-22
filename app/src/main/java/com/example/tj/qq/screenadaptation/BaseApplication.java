package com.example.tj.qq.screenadaptation;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.mob.MobSDK;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //对单位的自定义配置, 请在 App 启动时完成

        MobSDK.init(this);//初始化MobSDK

        configUnits();
        OkGo.getInstance().init(this);
    }

    private void configUnits() {
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(true)
                .setSupportSubunits(Subunits.MM);


    }
}
