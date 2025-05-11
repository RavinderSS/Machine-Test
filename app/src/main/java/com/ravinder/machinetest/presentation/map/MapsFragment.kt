package com.ravinder.machinetest.presentation.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ravinder.machinetest.R
import com.ravinder.machinetest.databinding.FragmentMapsBinding
import com.ravinder.machinetest.presentation.main.MarkerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding

    private var googleMap: GoogleMap? = null

    private val viewModel: MarkerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Observe markers immediately
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.markers.collectLatest {
                updateMarkers()
            }
        }

        // Load the map asynchronously
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { map ->
            googleMap = map
            setupMap()
            updateMarkers() // Apply markers when map is ready
        }

    }

    private fun updateMarkers() {
        val safeMap = googleMap ?: return
        safeMap.clear()

        viewModel.markers.value.forEach { marker ->
            safeMap.addMarker(
                MarkerOptions()
                    .position(LatLng(marker.latitude, marker.longitude))
                    .icon(BitmapFromVector(requireContext(), R.drawable.ic_marker))
            )
        }
    }

     fun BitmapFromVector(context: Context, vectorResId:Int): BitmapDescriptor? {
        //drawable generator
        var vectorDrawable: Drawable
        vectorDrawable= ContextCompat.getDrawable(context,vectorResId)!!
        vectorDrawable.setBounds(0,0,vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight)
        //bitmap genarator
        var bitmap: Bitmap
        bitmap= Bitmap.createBitmap(vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight,Bitmap.Config.ARGB_8888)
        //canvas genaret
        var canvas: Canvas
        //pass bitmap in canvas constructor
        canvas= Canvas(bitmap)
        //pass canvas in drawable
        vectorDrawable.draw(canvas)
        //return BitmapDescriptorFactory
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun setupMap() {
        googleMap?.setOnMapClickListener { latLng ->
            viewModel.addMarker(latLng.latitude, latLng.longitude)
        }
    }

}