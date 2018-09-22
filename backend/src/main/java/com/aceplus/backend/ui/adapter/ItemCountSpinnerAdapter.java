package com.aceplus.backend.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aceplus.backend.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kkk on 4/5/2018.
 */

public class ItemCountSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public ItemCountSpinnerAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.custom_spinner_item, null);
        TextView txt_spinner_item = (TextView) view.findViewById(R.id.tv_spinner_item);
        if (position < list.size()) {
            txt_spinner_item.setText(list.get(position));
//            txt_spinner_item.setId(list.get(position).getId());
        }
        return view;
    }

    public void setNewList(List<String> newList) {
        list = newList;
        notifyDataSetChanged();
    }


    public List<String> getNewList() {
        return list;
    }
}
