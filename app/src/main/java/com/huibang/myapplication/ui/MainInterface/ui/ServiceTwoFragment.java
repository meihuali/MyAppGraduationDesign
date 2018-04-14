package com.huibang.myapplication.ui.MainInterface.ui;

import android.support.v7.widget.Toolbar;


import com.huibang.myapplication.R;

import butterknife.BindView;

/**
 * Created by geyifeng on 2017/7/20.
 */

public class ServiceTwoFragment extends BaseTwoFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_two_service;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar
                .titleBar(toolbar)
                .init();
        //或者
        //ImmersionBar.setTitleBar(getActivity(),toolbar);
    }
}