package com.tencent.map.driver.page;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.map.driver.BaseActivity;
import com.tencent.map.driver.R;
import com.tencent.map.driver.synchro.driver.FastDriver;
import com.tencent.map.driver.synchro.driver.HitchHikeDriver;
import com.tencent.map.driver.util.CommonUtils;

public class MainActivity extends BaseActivity {

    static final int HITCH_HIKE_DERIVER = 0;// 顺风车
    static final int FAST_DRIVER = 1;// 快车

    private RecyclerView lsRv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_layout);
        ActionBar actionBar = super.getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        initRv();
    }

    private void initRv() {
        lsRv = findViewById(R.id.ls_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        lsRv.setLayoutManager(manager);
        lsRv.setAdapter(new RvAdapter(new RvAdapter.IClickListener() {
            @Override
            public void onClick(int itemPosition) {
                click(itemPosition);
            }
        }));
    }

    private void click(int position) {
        switch (position){
            case HITCH_HIKE_DERIVER:
                CommonUtils.toIntent(this, HitchHikeDriver.class);
                break;
            case FAST_DRIVER:
                CommonUtils.toIntent(this, FastDriver.class);
                break;
        }
    }
}
