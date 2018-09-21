package com.aceplus.hackthon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aceplus.hackthon.R;

import butterknife.ButterKnife;


/**
 * Created by kyawsanwin on 8/5/16.
 */
public class TodayMenuRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_CONTENT = 1;
    private final Context context;

    private int totalSize;
    private long movieId;


    public TodayMenuRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public void setTodayMenuList() {
        notifyDataSetChanged();
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
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SimpleViewHolder) {


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
        return 6;
    }

  /*  public MediaItem getItem(int position) {
        return this.movieList.get(position);
    }
*/

    public class SimpleViewHolder extends RecyclerView.ViewHolder {


        public SimpleViewHolder(View itemView) {
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
