package com.ertugrulakkaya.spaceexplorer.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.ertugrulakkaya.spaceexplorer.data.local.dao.LaunchDao
import com.ertugrulakkaya.spaceexplorer.data.local.entity.LaunchEntity

@Database(
    entities = [LaunchEntity::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(LaunchDatabaseConstructor::class)
abstract class LaunchDatabase : RoomDatabase() {

    abstract fun launchDao(): LaunchDao

    companion object {
        const val  DATABASE_NAME = "launch_database.db"

    }


}


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object LaunchDatabaseConstructor : RoomDatabaseConstructor<LaunchDatabase>



