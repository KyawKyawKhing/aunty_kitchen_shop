package com.aceplus.hackthon.shoplist;

/**
 * Created by kyawthetwin on 9/23/18.
 */

public class ShopList {

    String shopName;

    String shopPhone;

    public ShopList(String shopName, String shopPhone) {
        this.shopName = shopName;
        this.shopPhone = shopPhone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }
}
