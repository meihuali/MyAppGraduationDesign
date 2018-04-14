package com.huibang.myapplication.ui.MainInterface.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.huibang.myapplication.R;
import com.huibang.myapplication.ui.MainInterface.entity.HomeBean;
import com.huibang.myapplication.ui.MainInterface.homeAdapter.HomeAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by geyifeng on 2017/7/20.
 */

public class HomeTwoFragment extends BaseTwoFragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;




    List<HomeBean> mlist = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private HomeBean bean;
    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView rv;
    private int bannerHeight;

    @Override
    protected int setLayoutId() {
        return R.layout.fg_content_one;
    }

    @Override
    protected void initData(View view) {

        //遍历假数据
        intDatas();

        refreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.refreshLayout);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        refreshLayout.setEnableLoadmore(false);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter();
        homeAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rv.setAdapter(homeAdapter);
        addHeaderView();
        homeAdapter.setPreLoadNumber(1);
        homeAdapter.setNewData(mlist);

        adapterLinerst();
                /*
        *  下面 addOnSorollListenter接口是用来·监听recyview滚动来更改toorbar 的
        * */
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= bannerHeight) {
                    float alpha = (float) totalDy / bannerHeight;
                    toolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(getActivity(), R.color.juse), alpha));
                 //   toolbar.setBackground(getResources().getDrawable(R.drawable.test));
                } else {
                    toolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(getActivity(), R.color.juse), 1));
                //    toolbar.setBackground(getResources().getDrawable(R.drawable.test));
                }
            }
        });


        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                        toolbar.setVisibility(View.VISIBLE);
                    }
                }, 2000);
            }

            @Override
            public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
                toolbar.setVisibility(View.GONE);
            }

            @Override
            public void onPullDownReleasing(TwinklingRefreshLayout refreshLayout, float fraction) {
                if (Math.abs(fraction - 1.0f) > 0) {
                    toolbar.setVisibility(View.VISIBLE);
                } else {
                    toolbar.setVisibility(View.GONE);
                }
            }
        });



    }
    /*
    * 总item的点击事情
    * */
    private void adapterLinerst() {
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(),DetailsPageActivity.class);
                startActivity(intent);

            }
        });
    }




    private void addHeaderView() {
        View  headView = LayoutInflater.from(getActivity()).inflate(R.layout.item_banner, (ViewGroup) rv.getParent(), false);
        ImageView banner = (ImageView) headView.findViewById(R.id.banner);
        banner.setBackground(getActivity().getResources().getDrawable(R.drawable.test));
        homeAdapter.addHeaderView(headView);
        ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
        ViewGroup.LayoutParams titleBarParams = toolbar.getLayoutParams();
        bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());
    }


    //遍历假数据
    private void intDatas() {
        for (int i = 0; i < 200; i++) {
            bean = new HomeBean();
            bean.setTv_cantingname("广州麦当劳东圃餐厅");
            mlist.add(bean);
        }

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(toolbar).init();
    }
}
