package com.example.tj.qq.screenadaptation;

import com.mob.MobSDK;

import me.jessyan.autosize.AutoSizeConfig;
import me.jessyan.autosize.unit.Subunits;

public class BaseApplication extends com.qzb.common.baseapp.BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //对单位的自定义配置, 请在 App 启动时完成

        MobSDK.init(this);//初始化MobSDK

        configUnits();

    }

    private void configUnits() {
        AutoSizeConfig.getInstance().getUnitsManager()
                .setSupportDP(false)
                .setSupportSP(true)
                .setSupportSubunits(Subunits.MM);


    }
}
