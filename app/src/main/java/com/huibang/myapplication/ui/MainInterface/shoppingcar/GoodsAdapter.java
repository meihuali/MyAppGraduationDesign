package com.huibang.myapplication.ui.MainInterface.shoppingcar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.huibang.myapplication.R;
import com.huibang.myapplication.ui.MainInterface.ui.DianCanFragment;

import java.text.NumberFormat;
import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public class GoodsAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ArrayList<GoodsItem> dataList;
    private Context mContext;
    private NumberFormat nf;
    private LayoutInflater mInflater;
    DianCanFragment dianCanFragment;
    private GoodsItem item;

    public onItemOnClickLinerst MonItemOnClickLinerst;
    public interface onItemOnClickLinerst{
        void onItemOnClicks(View v,GoodsItem item,TextView tvMinus,TextView tvCounts);
    }
    public void setOnItemOnClickLinerst(onItemOnClickLinerst onItemOnClickLinerst){
        this.MonItemOnClickLinerst = onItemOnClickLinerst;
    }

    /*==========================================================================*/
    public onItemOnClickLinerstDle MonItemOnClickLinerstDle;
    public interface onItemOnClickLinerstDle{
        void onItemOnClicks(View v,GoodsItem item,TextView tvMinus,TextView tvCounts);
    }
    public void setOnItemOnClickLinerstDle(onItemOnClickLinerstDle onItemOnClickLinerstDle){
        this.MonItemOnClickLinerstDle = onItemOnClickLinerstDle;
    }


    public GoodsAdapter(ArrayList<GoodsItem> dataList, Context mContext,DianCanFragment dianCanFragment) {
        this.dataList = dataList;
        this.mContext = mContext;
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        this.dianCanFragment = dianCanFragment;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_header_view, parent, false);
        }
        ((TextView)(convertView)).setText(dataList.get(position).typeName);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return dataList.get(position).typeId;
    }

    @Override
    public int getCount() {
        if(dataList==null){
            return 0;
        }
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHolder holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_goods,parent,false);
            holder = new ItemViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ItemViewHolder) convertView.getTag();
        }
         item = dataList.get(position);
        holder.bindData(item);
        return convertView;
    }

    class ItemViewHolder implements View.OnClickListener{
        private TextView name,price,tvAdd,tvMinus,tvCount;
        private GoodsItem item;
        private RatingBar ratingBar;

        public ItemViewHolder(View itemView) {
            name = (TextView) itemView.findViewById(R.id.tvName);
            price = (TextView) itemView.findViewById(R.id.tvPrice);
            tvCount = (TextView) itemView.findViewById(R.id.count);
            tvMinus = (TextView) itemView.findViewById(R.id.tvMinus);
            tvAdd = (TextView) itemView.findViewById(R.id.tvAdd);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            tvMinus.setOnClickListener(this);
            tvAdd.setOnClickListener(this);
        }

        public void bindData(GoodsItem item){
            this.item = item;
            name.setText(item.name);
            ratingBar.setRating(item.rating);
            item.count = dianCanFragment.getSelectedItemCountById(item.id);
            tvCount.setText(String.valueOf(item.count));
            price.setText(nf.format(item.price));
            if(item.count<1){
                tvCount.setVisibility(View.GONE);
                tvMinus.setVisibility(View.GONE);
            }else{
                tvCount.setVisibility(View.VISIBLE);
                tvMinus.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tvAdd: {
                    if (MonItemOnClickLinerst != null) {
                        MonItemOnClickLinerst.onItemOnClicks(v,item,tvMinus,tvCount);
                    }
                }
                break;
                case R.id.tvMinus: {
                   /* int count = dianCanFragment.getSelectedItemCountById(item.id);
                    if (count < 2) {
                        tvMinus.setAnimation(getHiddenAnimation());
                        tvMinus.setVisibility(View.GONE);
                        tvCount.setVisibility(View.GONE);
                    }
                    count--;
                    dianCanFragment.remove(item, false);//activity.getSelectedItemCountById(item.id)
                    tvCount.setText(String.valueOf(count));*/
                    if (MonItemOnClickLinerstDle != null) {
                        MonItemOnClickLinerstDle.onItemOnClicks(v,item,tvMinus,tvCount);
                    }

                }
                break;
                default:
                    break;
            }
        }
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
