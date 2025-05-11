package com.ravinder.machinetest.di

import android.content.Context
import androidx.room.Room
import com.ravinder.machinetest.data.local.AppDatabase
import com.ravinder.machinetest.data.local.MarkerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "app_db"
        ).build()
    }

    @Provides
    fun provideMarkerDao(db: AppDatabase): MarkerDao = db.markerDao()
}
