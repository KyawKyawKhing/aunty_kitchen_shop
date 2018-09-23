package com.aceplus.hackthon.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aceplus.hackthon.R;
import com.aceplus.shared.VO.OrderItemVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by kyawsanwin on 8/5/16.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_CONTENT = 1;
    private final Context context;
    private final Activity activity;


    List<OrderItemVO> itemList;

    public HistoryListAdapter(Context context, Activity a) {
        this.context = context;
        this.activity = a;
    }


    public void setReportList(List<OrderItemVO> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /* if (viewType == VIEW_TYPE_LOADING) {
            final View view = LayoutInflater.from(context).inflate(R.layout.no_order_layout,
                    parent, false);
            return new LoadingViewHolder(view);
        } else {

        } */final View view = LayoutInflater.from(context).inflate(R.layout.special_order_listitem,
                parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder.getItemViewType() == VIEW_TYPE_CONTENT) {

            ((OrderViewHolder) holder).itemName.setText(itemList.get(position).getItemName());
            ((OrderViewHolder) holder).itemPrice.setText(itemList.get(position).getItemPrice());
            ((OrderViewHolder) holder).customerRemark.setText(itemList.get(position).getCustomerRemark());
            ((OrderViewHolder) holder).itemCount.setText(itemList.get(position).getItemCount() + "ပြဲ");

            if (itemList.get(position).getCustomerRemark().isEmpty()) {
                ((OrderViewHolder) holder).remark.setVisibility(View.GONE);
            } else {
                ((OrderViewHolder) holder).remark.setVisibility(View.VISIBLE);
            }
        } else if (holder.getItemViewType() == VIEW_TYPE_LOADING) {
           // ((TodayMenuRecyclerViewAdapter.LoadingViewHolder) holder).cantOrderLayout.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemViewType(int position) {
        //return position == 0 ? VIEW_TYPE_LOADING : VIEW_TYPE_CONTENT;
        return VIEW_TYPE_CONTENT;
    }

    @Override
    public long getItemId(int position) {
        //return (getItemViewType(position) == VIEW_TYPE_CONTENT) ? position : -1;
        return position;
    }

    @Override
    public int getItemCount() {
        //return movieList == null ? 0 : movieList.size() + 1;
        return itemList == null? 0 : itemList.size();
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemName)
        TextView itemName;
        @BindView(R.id.itemPrice)
        TextView itemPrice;
        @BindView(R.id.customerRemark)
        TextView customerRemark;
        @BindView(R.id.remark)
        LinearLayout remark;
        @BindView(R.id.itemCount)
        TextView itemCount;

        public OrderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

  /*  public class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.noOrderLayout)
        LinearLayout noOrderLayout;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        *//*@BindView(R.id.llProgressLoading)
        LinearLayout llProgressLoading;
        @BindView(R.id.listProgress)
        ProgressBar listProgress;
        @BindView(R.id.tvLoading)
        TextView tvLoading;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }*//*
    }*/
}
