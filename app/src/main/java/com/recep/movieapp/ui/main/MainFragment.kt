package com.recep.movieapp.ui.main

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.recep.movieapp.R
import com.recep.movieapp.databinding.MainFragmentBinding
import com.recep.movieapp.model.api.Result
import com.recep.movieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModels()

    private var dotList = ArrayList<TextView>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)

        initObserve()
    }

    private fun initObserve() {
        viewModel.nowPlayingResponse.observe(viewLifecycleOwner, Observer {
            val sliderList = it?.results?.take(Constants.SLIDER_ITEM_COUNT)

            sliderList?.let { list ->
                addDots(list.size, 0)
                binding.viewPager.adapter = SliderAdapter(requireContext(), list) { item ->
                    navigateDetailFragment(item)
                }

                binding.viewPager.addOnPageChangeListener(object :
                    ViewPager.OnPageChangeListener {
                    override fun onPageSelected(position: Int) {
                        addDots(list.size, position);
                    }

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageScrollStateChanged(state: Int) {}
                })
            }
        })

        viewModel.upcomingResponse.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter =
                MainListAdapter(it?.results) { item -> navigateDetailFragment(item) }
        })
    }

    private fun addDots(size: Int, position: Int) {
        dotList.clear()
        binding.dotLayout.removeAllViews()
        for (i in 0 until size) {
            dotList.add(TextView(requireContext()).apply {
                text = Html.fromHtml("•")
                textSize = 35F
                setTextColor(resources.getColor(R.color.gray_dark_color))
            })
            binding.dotLayout.addView(dotList[i])
        }
        if (dotList.size > 0) {
            dotList[position].setTextColor(resources.getColor(R.color.white))
        }
    }

    private fun navigateDetailFragment(item: Result?) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(item)
        findNavController().navigate(action)
    }
}