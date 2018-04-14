package com.huibang.myapplication.ui.MainInterface.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huibang.myapplication.R;

/**
 * Created by jsc on 2018/4/12.
 */

public class PingJiaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_diancan, container, false);
        return view;
    }

}
