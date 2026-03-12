package com.ertugrulakkaya.spaceexplorer.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ertugrulakkaya.spaceexplorer.data.local.LaunchDatabase
import com.ertugrulakkaya.spaceexplorer.data.local.LaunchDatabaseConstructor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<RoomDatabase.Builder<LaunchDatabase>> {
        val dbFile = androidContext().getDatabasePath(LaunchDatabase.DATABASE_NAME)
        Room.databaseBuilder<LaunchDatabase>(
            context = androidContext(),
            name = dbFile.absolutePath,
            factory = {
                LaunchDatabaseConstructor.initialize()
            }
        )
    }
}
