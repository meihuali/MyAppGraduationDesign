package com.huibang.myapplication.ui;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.huibang.myapplication.R;
import com.huibang.myapplication.ui.MainInterface.ui.BaseActivity;
import com.huibang.myapplication.ui.MainInterface.ui.CategoryTwoFragment;
import com.huibang.myapplication.ui.MainInterface.ui.HomeTwoFragment;
import com.huibang.myapplication.ui.MainInterface.ui.ServiceTwoFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.ll_home)
    LinearLayout ll_home;
    @BindView(R.id.ll_category)
    LinearLayout ll_category;
    @BindView(R.id.ll_service)
    LinearLayout ll_service;

    private HomeTwoFragment homeTwoFragment;
    private CategoryTwoFragment categoryTwoFragment;
    private ServiceTwoFragment serviceTwoFragment;



    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        selectedFragment(0);
        tabSelected(ll_home);
    }

    @Override
    protected void setListener() {
        ll_home.setOnClickListener(this);
        ll_category.setOnClickListener(this);
        ll_service.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(ll_home);
                break;
            case R.id.ll_category:
                selectedFragment(1);
                tabSelected(ll_category);
                break;
            case R.id.ll_service:
                selectedFragment(2);
                tabSelected(ll_service);
                break;

        }
    }

    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (homeTwoFragment == null) {
                    homeTwoFragment = new HomeTwoFragment();
                    transaction.add(R.id.content, homeTwoFragment);
                } else
                    transaction.show(homeTwoFragment);
                break;
            case 1:
                if (categoryTwoFragment == null) {
                    categoryTwoFragment = new CategoryTwoFragment();
                    transaction.add(R.id.content, categoryTwoFragment);
                } else
                    transaction.show(categoryTwoFragment);
                break;
            case 2:
                if (serviceTwoFragment == null) {
                    serviceTwoFragment = new ServiceTwoFragment();
                    transaction.add(R.id.content, serviceTwoFragment);
                } else
                    transaction.show(serviceTwoFragment);
                break;

        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (homeTwoFragment != null)
            transaction.hide(homeTwoFragment);
        if (categoryTwoFragment != null)
            transaction.hide(categoryTwoFragment);
        if (serviceTwoFragment != null)
            transaction.hide(serviceTwoFragment);

    }

    private void tabSelected(LinearLayout linearLayout) {
        ll_home.setSelected(false);
        ll_category.setSelected(false);
        ll_service.setSelected(false);

        linearLayout.setSelected(true);
    }
}
