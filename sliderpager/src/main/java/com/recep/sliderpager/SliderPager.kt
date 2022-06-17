package com.recep.sliderpager

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

class SliderPager : ConstraintLayout {

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!,
        attrs,
        defStyle
    ) {
        init()
    }

    private lateinit var viewPager: ViewPager
    private lateinit var dotLayout: LinearLayout
    private var dotList = mutableListOf<TextView>()
    private var sliderList = mutableListOf<SliderItem>()

    private var adapter: SliderAdapter = initAdapter()

    private fun initAdapter(): SliderAdapter {
        return SliderAdapter(context) { clickItem?.clickItem(it) }
    }

    private fun init() {
        inflate(context, R.layout.slider, this)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = adapter

        dotLayout = findViewById(R.id.dotLayout)

    }

    private var clickItem: ClickItem? = null

    interface ClickItem {
        fun clickItem(item: SliderItem?)
    }

    fun setClickItem(click: ClickItem) {
        clickItem = click
    }

    fun setItems(list: List<SliderItem>) {
        sliderList.addAll(list)
        addDots(sliderList.size, 0)
        adapter.updateItemList(list)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                dotList.clear()
                dotLayout.removeAllViews()
                addDots(sliderList.size, position)
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun addDots(size: Int, position: Int, view: LinearLayout = dotLayout) {
        for (i in 0 until size) {
            dotList.add(TextView(context).apply {
                text = Html.fromHtml("•")
                textSize = 35F
                setTextColor(ContextCompat.getColor(context, R.color.gray_dark_color))
            })
            view.addView(dotList[i])
        }
        if (dotList.size > 0) {
            dotList[position].setTextColor(ContextCompat.getColor(context, R.color.white))
        }
    }
}