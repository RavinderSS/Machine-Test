package com.ravinder.machinetest.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(marker: MarkerEntity):Long

    @Delete
    suspend fun delete(marker: MarkerEntity)

    @Query("SELECT * FROM markers")
    fun getAllMarkers(): Flow<List<MarkerEntity>>
}
