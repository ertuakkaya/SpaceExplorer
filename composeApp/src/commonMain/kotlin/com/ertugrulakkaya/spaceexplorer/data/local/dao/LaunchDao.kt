package com.ertugrulakkaya.spaceexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ertugrulakkaya.spaceexplorer.data.local.entity.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLaunches(launches: List<LaunchEntity>)

    @Query("SELECT * FROM LaunchEntity")
    fun getAllLaunches(): Flow<List<LaunchEntity>>

    @Query("DELETE FROM LaunchEntity")
    suspend fun deleteAllLaunches()



}