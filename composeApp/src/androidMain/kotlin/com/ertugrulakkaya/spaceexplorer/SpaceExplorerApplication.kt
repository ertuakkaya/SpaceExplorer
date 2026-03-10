package com.ertugrulakkaya.spaceexplorer // Kendi paket adınızı yazın

import android.app.Application
import com.ertugrulakkaya.spaceexplorer.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class SpaceExplorerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@SpaceExplorerApplication)
        }
    }
}