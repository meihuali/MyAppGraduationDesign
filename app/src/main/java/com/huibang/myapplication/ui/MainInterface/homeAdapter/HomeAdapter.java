package com.huibang.myapplication.ui.MainInterface.homeAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huibang.myapplication.R;
import com.huibang.myapplication.ui.MainInterface.entity.HomeBean;

/**
 * 项目名：MyAppGraduationDesign
 * 包名：com.huibang.myapplication.ui.MainInterface.homeAdapter
 * 文件名：HomeAdapter
 * 创建者 ：${梅华黎}
 * 创建时间： 2018/4/12 0012 0:00
 * 描述：TODO
 */
public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {

    public HomeAdapter() {
        super(R.layout.home_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
    }
}
