package com.tencent.map.driver.synchro.driver;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.tencent.map.driver.BaseActivity;
import com.tencent.map.driver.R;
import com.tencent.map.driver.synchro.driver.helper.SingleHelper;
import com.tencent.map.lsdriver.TSLDExtendManager;
import com.tencent.map.lsdriver.lsd.listener.DriDataListener;
import com.tencent.map.lssupport.bean.TLSBPosition;
import com.tencent.map.lssupport.bean.TLSBWayPoint;
import com.tencent.map.navi.car.CarNaviView;
import com.tencent.map.navi.car.TencentCarNaviManager;
import com.tencent.map.navi.ui.car.CarNaviInfoPanel;

import java.util.ArrayList;

public class DriverNaviActivity extends BaseActivity {

    private static final String LOG_TAG = "navi1234";

    TSLDExtendManager lsManager;// 司乘管理类
    TencentCarNaviManager mNaviManager;// 导航
    CarNaviView carNaviView;

    int curRouteIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_layout_navi_layout);
        carNaviView = findViewById(R.id.navi_car_view);

        curRouteIndex = getIntent().getIntExtra("route_index", 0);

        mNaviManager = SingleHelper.getNaviManager(getApplicationContext());
        mNaviManager.addNaviView(carNaviView);
        lsManager = TSLDExtendManager.getInstance();
        lsManager.addTLSDriverListener(new MyDriverListener());// 数据callback
        lsManager.addRemoveWayPointCallBack(new DriDataListener.IRemoveWayByUserCallBack() {
            @Override
            public void onRemoveWayPoint(ArrayList<TLSBWayPoint> wayPoints) {
                // 剔除途经点的回调
                Log.e(LOG_TAG, ">>>onRemoveWayPoint !!");
                // app->停止导航，重新算路，开始导航
            }
        });

        mNaviManager.setInternalTtsEnabled(true);
        CarNaviInfoPanel carNaviInfoPanel = carNaviView.showNaviInfoPanle();// 默认ui
        carNaviInfoPanel.setOnNaviInfoListener(new CarNaviInfoPanel.OnNaviInfoListener() {
            @Override
            public void onBackClick() {
                mNaviManager.stopSimulateNavi();
                finish();
            }
        });

        try {
            // 开始模拟导航
            mNaviManager.startSimulateNavi(curRouteIndex);
        }catch (Exception e) {
            Log.e(LOG_TAG, "start navi err : " + e.getMessage());
        }
    }

    /**
     * 剔除途经点上车点
     * @param view
     */
    public void RemoveWayStart(View view) {
        if(lsManager != null)
            lsManager.arrivedPassengerStartPoint("test_passenger_order_000011");
    }

    /**
     * 剔除途经点下车点
     * @param view
     */
    public void RemoveWayEnd(View view) {
        if(lsManager != null)
            lsManager.arrivedPassengerStartPoint("test_passenger_order_000011");
    }

    @Override
    public void onStart() {
        if (carNaviView != null) {
            carNaviView.onStart();
        }
        super.onStart();
    }

    @Override
    public void onRestart() {
        if (carNaviView != null) {
            carNaviView.onRestart();
        }
        super.onRestart();
    }

    @Override
    public void onResume() {
        if (carNaviView != null) {
            carNaviView.onResume();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        if (carNaviView != null) {
            carNaviView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onStop() {
        if (carNaviView != null) {
            carNaviView.onStop();
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        if (carNaviView != null) {
            carNaviView.onDestroy();
        }
        super.onDestroy();
    }

    /**
     * 数据回调
     */
    class MyDriverListener implements DriDataListener.ITLSDriverListener {
        @Override
        public void onPushRouteSuc() {
            Log.e(LOG_TAG, "navigation onPushRouteSuc()");
        }

        @Override
        public void onPushRouteFail(int errCode, String errMsg) {
            Log.e(LOG_TAG, "navigation onPushRouteFail()");
        }

        @Override
        public void onPushPositionSuc() {
            Log.e(LOG_TAG, "navigation onPushPositionSuc()");
        }

        @Override
        public void onPushPositionFail(int errCode, String errMsg) {
            Log.e(LOG_TAG, "navigation onPushPositionFail()");
        }

        @Override
        public void onPullLsInfoSuc(ArrayList<TLSBPosition> los) {
            Log.e(LOG_TAG, "navigation onPullLsInfoSuc()");
        }

        @Override
        public void onPullLsInfoFail(int errCode, String errMsg) {
            Log.e(LOG_TAG, "navigation onPullLsInfoFail()");
        }
    }

}
