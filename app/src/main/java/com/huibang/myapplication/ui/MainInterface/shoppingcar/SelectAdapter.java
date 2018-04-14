package com.huibang.myapplication.ui.MainInterface.shoppingcar;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huibang.myapplication.R;
import com.huibang.myapplication.ui.MainInterface.ui.DianCanFragment;

import java.text.NumberFormat;


public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder>{
    FragmentActivity mActivity;
    private SparseArray<GoodsItem> dataList;
    private NumberFormat nf;
    private LayoutInflater mInflater;
    DianCanFragment dianCanFragment;

    public onAddLinsert mOnAddLinsert;
    public interface onAddLinsert{
        void onAddbug(GoodsItem item);
    }
    public void setOnAddLinsert(onAddLinsert onAddLinsert) {
        this.mOnAddLinsert = onAddLinsert;
    }

    /*====================================================================================================*/
    public onRemoves mOnRemoves;
    public interface onRemoves{
        void onRemove(GoodsItem item);
    }

    public void setOnRemoves(onRemoves onRemoves) {
        this.mOnRemoves = onRemoves;
    }



    public SelectAdapter(FragmentActivity activity, SparseArray<GoodsItem> dataList,DianCanFragment dianCanFragment) {
        this.mActivity = activity;
        this.dataList = dataList;
        nf = NumberFormat.getCurrencyInstance();
        nf.setMaximumFractionDigits(2);
        mInflater = LayoutInflater.from(activity);
        this.dianCanFragment = dianCanFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_selected_goods,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GoodsItem item = dataList.valueAt(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if(dataList==null) {
            return 0;
        }
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private GoodsItem item;
        private TextView tvCost,tvCount,tvAdd,tvMinus,tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvCost = (TextView) itemView.findViewById(R.id.tvCost);
            tvCount = (TextView) itemView.findViewById(R.id.count);
            tvMinus = (TextView) itemView.findViewById(R.id.tvMinus);
            tvAdd = (TextView) itemView.findViewById(R.id.tvAdd);
            tvMinus.setOnClickListener(this);
            tvAdd.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.tvAdd:
                    if (mOnAddLinsert != null) {
                        mOnAddLinsert.onAddbug(item);
                    }

                    break;
                case R.id.tvMinus:
                    if (mOnRemoves != null) {
                         mOnRemoves.onRemove(item);
                    }

                    break;
                default:
                    break;
            }
        }

        public void bindData(GoodsItem item){
            this.item = item;
            tvName.setText(item.name);
            tvCost.setText(nf.format(item.count*item.price));
            tvCount.setText(String.valueOf(item.count));
        }
    }
}
