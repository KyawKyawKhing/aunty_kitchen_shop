package com.aceplus.hackthon.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aceplus.hackthon.CustomDialog;
import com.aceplus.hackthon.R;
import com.aceplus.shared.VO.AvailableItemVO;
import com.aceplus.shared.VO.UserVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by kyawsanwin on 8/5/16.
 */
public class TodayMenuRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_CONTENT = 1;
    private final Context context;
    private final Activity activity;

    private int totalSize;
    private long movieId;
    List<AvailableItemVO> itemList;
    UserVO userVO;

    public TodayMenuRecyclerViewAdapter(Context context, Activity a) {
        this.context = context;
        this.activity = a;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public void setTodayMenuList(List<AvailableItemVO> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void setUserVo(UserVO userVo){
        this.userVO = userVo;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
         /*   final View view = LayoutInflater.from(context).inflate(R.layout.more_recommend_loading_view_layout,
                    parent, false);
            return new LoadingViewHolder(view);*/
        }
        final View view = LayoutInflater.from(context).inflate(R.layout.menu_item,
                parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OrderViewHolder) {
            if (itemList.get(position).getItemCount() != null && itemList.get(position).getItemCount() != 0) {
                holder.itemView.setVisibility(View.VISIBLE);
                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,330);
                layoutParams.setMargins(16,16,16,16);
               holder.itemView.setLayoutParams(layoutParams);

                ((OrderViewHolder) holder).tvItemName.setText(itemList.get(position).getItemName());
                ((OrderViewHolder) holder).tvItemPrice.setText(String.valueOf(itemList.get(position).getItemPrice()));
                ((OrderViewHolder) holder).tvRemaining.setText(String.valueOf(itemList.get(position).getItemCount())+" Remaining");
                ((OrderViewHolder) holder).btnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CustomDialog customDialog = new CustomDialog(activity,itemList.get(position).getItemCount(),userVO,itemList.get(position).getItemId(),itemList.get(position).getItemName(),itemList.get(position).getItemPrice());
                        customDialog.show();
                        customDialog.setCanceledOnTouchOutside(false);
                    }
                });
            }else {
               holder.itemView.setVisibility(View.GONE);
               holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0,0));
            }

        } else if (holder instanceof LoadingViewHolder) {
           /* if (position >= totalSize && totalSize > 0) {
                ((LoadingViewHolder) holder).llProgressLoading.setVisibility(View.GONE);
                ((LoadingViewHolder) holder).listProgress.setVisibility(View.GONE);
                Log.d("Position greter", position + "");
            } else {
                ((LoadingViewHolder) holder).llProgressLoading.setVisibility(View.VISIBLE);
                ((LoadingViewHolder) holder).listProgress.setVisibility(View.VISIBLE);
                Log.d("Position", position + "");
            }
            Log.d("Position", "totalsize" + totalSize);
        }*/
        }

    }


    @Override
    public int getItemViewType(int position) {
        //return (position >= movieList.size()) ? VIEW_TYPE_LOADING : VIEW_TYPE_CONTENT;
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


  /*  public MediaItem getItem(int position) {
        return this.movieList.get(position);
    }
*/

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_itemName)
        TextView tvItemName;
        @BindView(R.id.txt_itemPrice)
        TextView tvItemPrice;
        @BindView(R.id.txt_remaining)
        TextView tvRemaining;
        @BindView(R.id.btn_order)
        Button btnOrder;

        public OrderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
        /*@BindView(R.id.llProgressLoading)
        LinearLayout llProgressLoading;
        @BindView(R.id.listProgress)
        ProgressBar listProgress;
        @BindView(R.id.tvLoading)
        TextView tvLoading;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }*/
    }
}
