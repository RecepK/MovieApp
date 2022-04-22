package com.recep.movieapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.recep.movieapp.R
import com.recep.movieapp.databinding.DetailFragmentBinding
import com.recep.movieapp.model.api.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val item: Result? by lazy {
        arguments?.let {
            DetailFragmentArgs.fromBundle(it).item
        }
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DetailFragmentBinding.bind(view)

        item?.let { viewModel.getMovieDetail(it.id) }

        viewModel.movieDetailResponse.observe(viewLifecycleOwner, Observer {
            it?.let { binding.item = it }
        })
    }
}
