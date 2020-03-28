package com.tencent.map.driver;

import android.app.Application;

import com.tencent.map.navi.TencentNavi;
import com.tencent.navi.surport.utils.DeviceUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TencentNavi.Config config = new TencentNavi.Config();
        config.setDeviceId(DeviceUtils.getImei(getApplicationContext()));
        TencentNavi.init(this, config);
    }
}
