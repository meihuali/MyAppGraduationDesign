package com.huibang.myapplication.ui.MainInterface.shoppingcar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huibang.myapplication.R;
import com.huibang.myapplication.ui.MainInterface.ui.DianCanFragment;

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    public int selectTypeId;
    public ArrayList<GoodsItem> dataList;
    DianCanFragment dianCanFragment;
    Context activity;

    public onItemClickListener mOnItemClickListener;

    public interface onItemClickListener
    {
        void onItemClick(View view ,int position,GoodsItem item);
    }
    public void setOnItemClickLitsener(onItemClickListener mOnItemClickLitsener)
    {
        mOnItemClickListener= mOnItemClickLitsener;
    }
     /*-------------------------------------------------------------------------------------------------------------------------*/
     public onItemClickListenerCoutn mOnItemClickListenerCoutn;

    public interface onItemClickListenerCoutn
    {
        void onItemClick(GoodsItem item,TextView tvCount,View itemView,int selectTypeId,TextView type);
    }
    public void setOnItemClickLitsenerCoutn(onItemClickListenerCoutn mOnItemClickLitsenerCoutn)
    {
        mOnItemClickListenerCoutn= mOnItemClickLitsenerCoutn;
    }



    public TypeAdapter(Context activity, ArrayList<GoodsItem> dataList, DianCanFragment dianCanFragment) {
        this.activity = activity;
        this.dataList = dataList;
        this.dianCanFragment = dianCanFragment;
    }







    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final GoodsItem item = dataList.get(position);
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 int pos = holder.getLayoutPosition();
                 mOnItemClickListener.onItemClick(holder.itemView,pos,item);
             }
         });
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        if(dataList==null){
            return 0;
        }
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCount,type;
        private GoodsItem item;


        public ViewHolder(View itemView) {
            super(itemView);

            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
            type = (TextView) itemView.findViewById(R.id.type);
        }

        public void bindData(GoodsItem item){
/*            this.item = item;
            type.setText(item.typeName);

            int count = dianCanFragment.getSelectedGroupCountByTypeId(item.typeId);

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
            }*/


            if (mOnItemClickListenerCoutn != null) {
                mOnItemClickListenerCoutn.onItemClick(item,tvCount,itemView,selectTypeId,type);
            }

        }

  /*      @Override
        public void onClick(View v) {
         //   dianCanFragment.onTypeClicked(item.typeId);
        }*/
    }
}
