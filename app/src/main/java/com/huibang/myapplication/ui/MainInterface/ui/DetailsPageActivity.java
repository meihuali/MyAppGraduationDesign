package com.huibang.myapplication.ui.MainInterface.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

import com.gyf.barlibrary.ImmersionBar;
import com.huibang.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsc on 2018/4/12.
 * 店铺 详情页面
 */

public class DetailsPageActivity extends AppCompatActivity {
    ViewPager mViewPager;
    List<Fragment> mFragments;
    Toolbar mToolbar;
    private ArrayList<String> mTitle;
    private ArrayList<Fragment> mFragment;
    private TabLayout mTabLayout;
    private CollapsingToolbarLayout ctl_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailspage);

        initView();
    }
    /*
    * 初始化系列
    * */
    private void initView() {
        mViewPager=(ViewPager)findViewById(R.id.viewpager);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);

        ctl_bar = (CollapsingToolbarLayout) findViewById(R.id.ctl_bar);
        //设置CollapsingToolbarLayout 滑动到顶部后·的颜色
        ctl_bar.setContentScrimColor(getResources().getColor(R.color.colorPrimaryDark));
        ctl_bar.setCollapsedTitleGravity(Gravity.LEFT);//设置收缩后标题的位置
        // ctl_bar.setExpandedTitleGravity(Gravity.LEFT);////设置展开后标题的位置
        ctl_bar.setExpandedTitleColor(Color.WHITE);//设置展开后标题的颜色
        ctl_bar.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后标题的颜色

        //图片沉浸式
        ImmersionBar.with(this).titleBar(R.id.toolbar).init();



        mToolbar.setTitle("欢迎您使用懒人外卖");
        //下面这两句·是设置toorbar 返回箭头 true表示返回显示返回箭头
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void  setupViewPager(){
        mViewPager = (ViewPager) findViewById(R.id.viewpager);


        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        mTitle = new ArrayList<>();
        mTitle.add("点餐");
        mTitle.add("评价");

        mFragment = new ArrayList<>();
        mFragment.add(new DianCanFragment());
        mFragment.add(new PingJiaFragment());

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //mViewPager滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回item的个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
