package com.recep.movieapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.recep.movieapp.R
import com.recep.movieapp.databinding.MainFragmentBinding
import com.recep.movieapp.model.api.Result
import com.recep.movieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)

        initObserve()
    }

    private fun initObserve() {
        viewModel.nowPlayingResponse.observe(viewLifecycleOwner, Observer {
            val sliderList = it?.results?.take(Constants.SLIDER_ITEM_COUNT)
        })

        viewModel.upcomingResponse.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter =
                MainListAdapter(it?.results) { item -> navigateDetailFragment(item) }
        })
    }

    private fun navigateDetailFragment(item: Result?) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(item)
        findNavController().navigate(action)
    }
}