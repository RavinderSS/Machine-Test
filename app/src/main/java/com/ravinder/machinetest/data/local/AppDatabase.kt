package com.ravinder.machinetest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MarkerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun markerDao(): MarkerDao
}
