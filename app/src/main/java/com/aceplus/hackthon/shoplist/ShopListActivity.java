package com.aceplus.hackthon.shoplist;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aceplus.hackthon.R;
import com.aceplus.hackthon.Utils.ShopListDelegate;
import com.aceplus.hackthon.adapter.ShopListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kyawthetwin on 9/23/18.
 */

public class ShopListActivity extends AppCompatActivity implements ShopListDelegate {

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
        askForPermission(this, android.Manifest.permission.CALL_PHONE,1);
    }

    public static void askForPermission(Activity activity, String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
            }
        }
    }

    private void setUpRcvShopList(){
        shopListAdapter = new ShopListAdapter(this, ShopListActivity.this);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rcvShopList.setLayoutManager(mLayoutManager);
        rcvShopList.setAdapter(shopListAdapter);
    }

    @OnClick(R.id.imgBack)
    public void goBack(){
        onBackPressed();
    }

    @Override
    public void callPhone(String phone) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        String shopPhone = "tel:"+phone;
        callIntent.setData(Uri.parse(shopPhone));


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);
    }
}
