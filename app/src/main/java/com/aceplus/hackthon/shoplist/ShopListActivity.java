package com.aceplus.hackthon.shoplist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aceplus.hackthon.R;
import com.aceplus.hackthon.adapter.ShopListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/23/18.
 */

public class ShopListActivity extends AppCompatActivity {

    @BindView(R.id.rcv_shopList)
    RecyclerView rcvShopList;
    ShopListAdapter shopListAdapter;
    ArrayList<ShopList> shopLists;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        shopLists = new ArrayList<>();
        ShopList onetwofour = new ShopList("124","09 123456 789");
        ShopList choice = new ShopList("Choice","09 123456 789");
        ShopList orientalHouse = new ShopList("Oriental House","09 123456 789");
        ShopList Zeus = new ShopList("Zeus","09 123456 789");
        ShopList Jdonut = new ShopList("J donut","09 123456 789");
        shopLists.add(onetwofour);
        shopLists.add(choice);
        shopLists.add(orientalHouse);
        shopLists.add(Zeus);
        shopLists.add(Jdonut);

        init();

    }

    private void init(){
        ButterKnife.bind(this);
        setUpRcvShopList();
        shopListAdapter.setShopLists(shopLists);
    }

    private void setUpRcvShopList(){
        shopListAdapter = new ShopListAdapter(this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcvShopList.setLayoutManager(mLayoutManager);
        rcvShopList.setAdapter(shopListAdapter);
    }

    @OnClick(R.id.imgBack)
    public void goBack(){
        onBackPressed();
    }
}
