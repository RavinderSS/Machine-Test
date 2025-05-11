package com.ravinder.machinetest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravinder.machinetest.data.local.MarkerEntity
import com.ravinder.machinetest.domain.repository.MarkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarkerViewModel @Inject constructor(
    private val repository: MarkerRepository
) : ViewModel() {

    val markers = repository.getAllMarkers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addMarker(lat: Double, lng: Double) {
        viewModelScope.launch {
            repository.insert(MarkerEntity(latitude = lat, longitude = lng))
        }
    }

    fun deleteMarker(marker: MarkerEntity) {
        viewModelScope.launch {
            repository.delete(marker)
        }
    }
}

