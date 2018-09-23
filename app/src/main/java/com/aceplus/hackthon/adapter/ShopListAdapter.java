package com.aceplus.hackthon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aceplus.hackthon.R;
import com.aceplus.hackthon.Utils.ShopListDelegate;
import com.aceplus.hackthon.shoplist.ShopList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by kyawsanwin on 8/5/16.
 */
public class ShopListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_CONTENT = 1;
    private final Context context;
    private final ShopListDelegate delegate;
    List<ShopList> shopLists;


    public ShopListAdapter(Context context, ShopListDelegate delegate) {
        this.context = context;
        this.delegate = delegate;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_LOADING) {
         /*   final View view = LayoutInflater.from(context).inflate(R.layout.more_recommend_loading_view_layout,
                    parent, false);
            return new LoadingViewHolder(view);*/
        }
        final View view = LayoutInflater.from(context).inflate(R.layout.shop_item,
                parent, false);

        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ShopViewHolder) {
            ((ShopViewHolder) holder).shopName.setText(shopLists.get(position).getShopName());
            ((ShopViewHolder) holder).shopPhNo.setText(shopLists.get(position).getShopPhone());
            ((ShopViewHolder) holder).imgV_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delegate.callPhone(shopLists.get(position).getShopPhone());
                }


            });




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

    public void setShopLists(List<ShopList> shopLists){
        this.shopLists = shopLists;
        notifyDataSetChanged();
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
        return shopLists == null ? 0 : shopLists.size();
    }


    public class ShopViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_shopName)
        TextView shopName;
        @BindView(R.id.txt_phone)
        TextView shopPhNo;
        @BindView(R.id.imgV_call)
        ImageView imgV_call;

        public ShopViewHolder(View itemView) {
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