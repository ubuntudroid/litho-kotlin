package com.ubuntudroid.lithotest

import android.app.Application
import com.facebook.litho.LithoWebKitInspector
import com.facebook.soloader.SoLoader
import com.facebook.stetho.Stetho

class LithoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(LithoWebKitInspector(this))
                        .build())

    }
}