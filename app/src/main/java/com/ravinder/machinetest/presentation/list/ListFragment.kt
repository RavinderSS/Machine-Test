package com.ravinder.machinetest.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravinder.machinetest.databinding.FragmentListBinding
import com.ravinder.machinetest.presentation.main.MarkerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel: MarkerViewModel by activityViewModels()

    private lateinit var adapter: MarkerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MarkerAdapter { marker ->
            viewModel.deleteMarker(marker)
        }

        binding.rvMarkerList.adapter = adapter
        binding.rvMarkerList.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.markers.collectLatest { markers ->
                adapter.submitList(markers)  // Passes data into adapter
            }
        }
    }
}