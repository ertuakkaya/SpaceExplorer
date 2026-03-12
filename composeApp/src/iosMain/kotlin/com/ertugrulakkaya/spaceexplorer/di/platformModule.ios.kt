package com.ertugrulakkaya.spaceexplorer.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.ertugrulakkaya.spaceexplorer.data.local.LaunchDatabase
import com.ertugrulakkaya.spaceexplorer.data.local.LaunchDatabaseConstructor
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUserDomainMask

actual val platformModule: Module = module {
    single<RoomDatabase.Builder<LaunchDatabase>> {
        val dbFilePath = documentDirectory() + "/" + LaunchDatabase.DATABASE_NAME
        Room.databaseBuilder<LaunchDatabase>(
            name = dbFilePath,
            factory = {
                LaunchDatabaseConstructor.initialize()
            }
        ).setDriver(androidx.sqlite.driver.bundled.BundledSQLiteDriver())

    }


}



@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )
    return documentDirectory?.path ?: ""
}
