package com.huibang.myapplication.ui.MainInterface.ui;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huibang.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by geyifeng on 2017/7/20.
 */

public class ServiceTwoFragment extends BaseTwoFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.setting)
    ImageButton setting;
    @BindView(R.id.toolbars)
    RelativeLayout toolbars;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_tips)
    TextView tvUserTips;
    @BindView(R.id.login)
    RelativeLayout login;
    @BindView(R.id.shouhuodizhi)
    LinearLayout shouhuodizhi;
    @BindView(R.id.wodeshoucang)
    LinearLayout wodeshoucang;
    @BindView(R.id.wodecefu)
    LinearLayout wodecefu;
    @BindView(R.id.huanyingpingfen)
    LinearLayout huanyingpingfen;
    @BindView(R.id.jaimenghezuo)
    LinearLayout jaimenghezuo;
    Unbinder unbinder;

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

    }

    @Override
    protected void initData(View view) {
        unbinder = ButterKnife.bind(this, view);

        super.initData(view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.setting, R.id.toolbars, R.id.login, R.id.shouhuodizhi, R.id.wodeshoucang, R.id.wodecefu, R.id.huanyingpingfen, R.id.jaimenghezuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                Toast.makeText(mActivity, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.toolbars:
                Toast.makeText(mActivity, "设置1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login:
                Toast.makeText(mActivity, "登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.shouhuodizhi:
                Toast.makeText(mActivity, "收货地址", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wodeshoucang:
                Toast.makeText(mActivity, "我的收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wodecefu:
                Toast.makeText(mActivity, "我的客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.huanyingpingfen:
                Toast.makeText(mActivity, "欢迎评分", Toast.LENGTH_SHORT).show();
                break;
            case R.id.jaimenghezuo:
                Toast.makeText(mActivity, "加盟合作", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
