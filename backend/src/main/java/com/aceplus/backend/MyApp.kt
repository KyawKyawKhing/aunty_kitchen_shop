package com.aceplus.backend

import android.app.Application
import com.aceplus.shared.model.BackendModel

/**
 * Created by kkk on 9/22/2018.
 */
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        BackendModel.initBackendModel(applicationContext)
    }
}