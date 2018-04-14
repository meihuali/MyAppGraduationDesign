package com.huibang.myapplication.ui.MainInterface.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.huibang.myapplication.R;
import com.huibang.myapplication.ui.MainInterface.shoppingcar.DividerDecoration;
import com.huibang.myapplication.ui.MainInterface.shoppingcar.GoodsAdapter;
import com.huibang.myapplication.ui.MainInterface.shoppingcar.GoodsItem;
import com.huibang.myapplication.ui.MainInterface.shoppingcar.SelectAdapter;
import com.huibang.myapplication.ui.MainInterface.shoppingcar.TypeAdapter;

import java.text.NumberFormat;
import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by jsc on 2018/4/12.
 */

public class DianCanFragment extends Fragment  implements View.OnClickListener{

    private ImageView imgCart;
    private ViewGroup anim_mask_layout;
    private RecyclerView rvType,rvSelected;
    private TextView tvCount,tvCost,tvSubmit,tvTips;
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private StickyListHeadersListView listView;


    private ArrayList<GoodsItem> dataList,typeList;
    private SparseArray<GoodsItem> selectedList  = new SparseArray<>();
  private SparseIntArray groupSelect= new SparseIntArray();

    private GoodsAdapter myAdapter;
    private SelectAdapter selectAdapter;
    private TypeAdapter typeAdapter;

    private NumberFormat nf;
    private Handler mHanlder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_shopping_cart, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mHanlder = new Handler(Looper.getMainLooper());
        dataList = GoodsItem.getGoodsList();
        typeList = GoodsItem.getTypeList();
       // selectedList = new SparseArray<>();
//        groupSelect = new SparseIntArray();
        initViews(view);

    }

    public void initViews(View view) {
        tvCount = (TextView)view.findViewById(R.id.tvCount);
        tvCost = (TextView) view.findViewById(R.id.tvCost);
        tvTips = (TextView) view.findViewById(R.id.tvTips);
        tvSubmit  = (TextView) view.findViewById(R.id.tvSubmit);
        rvType = (RecyclerView) view.findViewById(R.id.typeRecyclerView);

        imgCart = (ImageView) view.findViewById(R.id.imgCart);
        anim_mask_layout = (RelativeLayout) view.findViewById(R.id.containerLayout);
        bottomSheetLayout = (BottomSheetLayout) view.findViewById(R.id.bottomSheetLayout);

        listView = (StickyListHeadersListView) view.findViewById(R.id.itemListView);

        rvType.setLayoutManager(new LinearLayoutManager(getActivity()));
        typeAdapter = new TypeAdapter(getActivity(),typeList,new DianCanFragment());

        rvType.setAdapter(typeAdapter);
        rvType.addItemDecoration(new DividerDecoration(getActivity()));

        myAdapter = new GoodsAdapter(dataList,getActivity(),new DianCanFragment());
        listView.setAdapter(myAdapter);
        /*
        * 这里就是右边的增加商品
        * */
        myAdapter.setOnItemOnClickLinerst(new GoodsAdapter.onItemOnClickLinerst() {
            @Override
            public void onItemOnClicks(View v,GoodsItem item ,TextView tvMinus,TextView tvCounts) {
               int count =  getSelectedItemCountById(item.id);
                if (count < 1) {
                    tvMinus.setAnimation(getShowAnimation());
                    tvMinus.setVisibility(View.VISIBLE);
                    tvCounts.setVisibility(View.VISIBLE);
                }
                add(item, false);
                count++;
                tvCounts.setText(String.valueOf(count));
                int[] loc = new int[2];
                v.getLocationInWindow(loc);
                playAnimation(loc);
            }
        });

        /*
        * 右边的减少商品
        * */
        myAdapter.setOnItemOnClickLinerstDle(new GoodsAdapter.onItemOnClickLinerstDle() {
            @Override
            public void onItemOnClicks(View v, GoodsItem item, TextView tvMinus, TextView tvCounts) {
                int count = getSelectedItemCountById(item.id);
                if (count < 2) {
                    tvMinus.setAnimation(getHiddenAnimation());
                    tvMinus.setVisibility(View.GONE);
                    tvCount.setVisibility(View.GONE);
                }
                count--;
                remove(item, false);
              //  tvCounts.setText(String.valueOf(count));
                tvCounts.setText("");
            }
        });

        /*
        * 这个是左边纵向列表上的小红点数量
        * */
        typeAdapter.setOnItemClickLitsenerCoutn(new TypeAdapter.onItemClickListenerCoutn() {
            @Override
            public void onItemClick(GoodsItem item, TextView tvCount, View itemView,int selectTypeId,TextView type) {
                type.setText(item.typeName);
                int count = getSelectedGroupCountByTypeId(item.typeId);
                tvCount.setText(String.valueOf(count));
                if(count<1){
                    tvCount.setVisibility(View.GONE);
                }else{
                    tvCount.setVisibility(View.VISIBLE);
                }
                if(item.typeId==selectTypeId){
                    itemView.setBackgroundColor(Color.WHITE);
                }else{
                    itemView.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });





        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                GoodsItem item = dataList.get(firstVisibleItem);
                if(typeAdapter.selectTypeId != item.typeId) {
                    typeAdapter.selectTypeId = item.typeId;
                    typeAdapter.notifyDataSetChanged();
                    rvType.smoothScrollToPosition(getSelectedGroupPosition(item.typeId));
                }
            }
        });
        /*
        *  这个就是点击左边的菜单调用的接口回调
        * */
        typeAdapter.setOnItemClickLitsener(new TypeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GoodsItem item) {

                listView.setSelection(getSelectedPosition(item.typeId));

            }
        });
    }

    public void playAnimation(int[] start_location){
        ImageView img = new ImageView(getActivity());
        img.setImageResource(R.drawable.button_add);
        setAnim(img,start_location);
    }

    private Animation createAnim(int startX, int startY){
        int[] des = new int[2];
        imgCart.getLocationInWindow(des);
        AnimationSet set = new AnimationSet(false);
        Animation translationX = new TranslateAnimation(0, des[0]-startX, 0, 0);
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1]-startY);
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1,0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    private void addViewToAnimLayout(final ViewGroup vg, final View view,int[] location){
        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y-loc[1]);
        vg.addView(view);
    }

    private void setAnim(final View v, int[] start_location) {
        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(start_location[0],start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(final Animation animation) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                    }
                },100);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        v.startAnimation(set);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bottom:
                showBottomSheet();
                break;
            case R.id.clear:
                clearCart();
                break;
            case R.id.tvSubmit:
                Toast.makeText(getActivity(), "结算", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    //添加商品
    public void add(GoodsItem item,boolean refreshGoodList){

        int groupCount = groupSelect.get(item.typeId);
        if(groupCount==0){
            groupSelect.append(item.typeId,1);
        }else{
            groupSelect.append(item.typeId,++groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if(temp==null){
            item.count=1;
            selectedList.append(item.id,item);
        }else{
            temp.count++;
        }
        update(refreshGoodList);
    }
    //移除商品
    public void remove(GoodsItem item,boolean refreshGoodList){

        int groupCount = groupSelect.get(item.typeId);
        if(groupCount==1){
            groupSelect.delete(item.typeId);
        }else if(groupCount>1){
            groupSelect.append(item.typeId,--groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if(temp!=null){
            if(temp.count<2){
                selectedList.remove(item.id);
            }else{
                item.count--;
            }
        }
        update(refreshGoodList);
    }
    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList){
        int size = selectedList.size();
        int count =0;
        double cost = 0;
        for(int i=0;i<size;i++){
            GoodsItem item = selectedList.valueAt(i);
            count += item.count;
            cost += item.count*item.price;
        }

        if(count<1){
            tvCount.setVisibility(View.GONE);
        }else{
            tvCount.setVisibility(View.VISIBLE);
        }

       tvCount.setText(String.valueOf(count));

        if(cost > 99.99){
            tvTips.setVisibility(View.GONE);
            tvSubmit.setVisibility(View.VISIBLE);
        }else{
            tvSubmit.setVisibility(View.GONE);
            tvTips.setVisibility(View.VISIBLE);
        }

        tvCost.setText(nf.format(cost));

        if(myAdapter!=null && refreshGoodList){
            myAdapter.notifyDataSetChanged();
        }
        if(selectAdapter!=null){
            selectAdapter.notifyDataSetChanged();
        }
        if(typeAdapter!=null){
            typeAdapter.notifyDataSetChanged();
        }
        if(bottomSheetLayout.isSheetShowing() && selectedList.size()<1){
            bottomSheetLayout.dismissSheet();
        }
    }

    private void showBottomSheet(){
        if(bottomSheet==null){
            bottomSheet = createBottomSheetView();
        }
        if(bottomSheetLayout.isSheetShowing()){
            bottomSheetLayout.dismissSheet();
        }else {
            if(selectedList.size()!=0){
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }

    //清空购物车
    public void clearCart(){
        selectedList.clear();
        groupSelect.clear();
        update(true);
    }

    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id){
        GoodsItem temp = selectedList.get(id);
        if(temp==null){
            return 0;
        }
        return temp.count;
    }



    //根据类别Id获取属于当前类别的数量
    public int getSelectedGroupCountByTypeId(int typeId){
       int aa =  groupSelect.get(typeId);
        return aa;
    }



    //根据类别id获取分类的Position 用于滚动左侧的类别列表
    public int getSelectedGroupPosition(int typeId){
        for(int i=0;i<typeList.size();i++){
            if(typeId==typeList.get(i).typeId){
                return i;
            }
        }
        return 0;
    }

/*    public void onTypeClicked(int typeId){

    }*/


    private int getSelectedPosition(int typeId){
        int position = 0;
        for(int i=0;i<dataList.size();i++){
            if(dataList.get(i).typeId == typeId){
                position = i;
                break;
            }
        }
        return position;
    }

    private View createBottomSheetView(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_bottom_sheet,(ViewGroup) getActivity().getWindow().getDecorView(),false);
        rvSelected = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        rvSelected.setLayoutManager(new LinearLayoutManager(getActivity()));
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(this);
        selectAdapter = new SelectAdapter(getActivity(),selectedList,new DianCanFragment());
        rvSelected.setAdapter(selectAdapter);


        selectAdapter.setOnAddLinsert(new SelectAdapter.onAddLinsert() {
            @Override
            public void onAddbug(GoodsItem item) {
                add(item,true);
            }
        });

        selectAdapter.setOnRemoves(new SelectAdapter.onRemoves() {
            @Override
            public void onRemove(GoodsItem item) {
                remove(item,true);
            }
        });

        return view;
    }


    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

}
