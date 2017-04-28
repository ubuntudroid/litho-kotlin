package com.ubuntudroid.lithotest

import android.app.Application
import com.facebook.litho.LithoWebKitInspector
import com.facebook.soloader.SoLoader
import com.facebook.stetho.Stetho
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.autoAndroidModule
import com.ubuntudroid.lithotest.network.GitHubService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class LithoApplication: Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(autoAndroidModule(this@LithoApplication))
        bind<Retrofit>("github") with singleton {
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()
        }
        bind<GitHubService>() with singleton { instance<Retrofit>("github").create(GitHubService::class.java) }
    }

    override fun onCreate() {
        super.onCreate()

        SoLoader.init(this, false)
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(LithoWebKitInspector(this))
                        .build())

    }
}