package com.aceplus.hackthon;

import android.app.Application;

import com.aceplus.shared.model.BackendModel;

/**
 * Created by kyawthetwin on 9/22/18.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BackendModel.Companion.initBackendModel(getApplicationContext());
    }
}
