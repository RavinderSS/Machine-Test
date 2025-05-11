package com.ravinder.machinetest.domain.repository

import com.ravinder.machinetest.data.local.MarkerDao
import com.ravinder.machinetest.data.local.MarkerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarkerRepository @Inject constructor(private val dao: MarkerDao) {
    suspend fun insert(marker: MarkerEntity) = dao.insert(marker)
    suspend fun delete(marker: MarkerEntity) = dao.delete(marker)
    fun getAllMarkers(): Flow<List<MarkerEntity>> = dao.getAllMarkers()
}
