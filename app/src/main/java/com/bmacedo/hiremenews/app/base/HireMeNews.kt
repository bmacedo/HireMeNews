package com.bmacedo.hiremenews.app.base

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.bmacedo.hiremenews.app.injection.components.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class HireMeNews : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = androidInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initDependencyInjection()
    }

    open fun initDependencyInjection() {
        DaggerAppComponent
            .create()
            .inject(this)
    }

}