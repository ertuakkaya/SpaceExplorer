package com.ertugrulakkaya.spaceexplorer.di

import androidx.room.Room
import com.ertugrulakkaya.spaceexplorer.data.local.LaunchDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single {
        val dbFile = androidContext().getDatabasePath(LaunchDatabase.DATABASE_NAME)
        Room.databaseBuilder<LaunchDatabase>(
            context = androidContext(),
            name = dbFile.absolutePath
        )
    }
}
