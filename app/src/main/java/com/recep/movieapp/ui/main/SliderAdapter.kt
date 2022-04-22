package com.recep.movieapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.recep.movieapp.R
import com.recep.movieapp.model.api.Result
import com.squareup.picasso.Picasso

class SliderAdapter(
    private val context: Context,
    private val list: List<Result>?,
    private val clickItem: (Result?) -> Unit
) : PagerAdapter() {

    override fun getCount(): Int = list?.size ?: 0

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.slider_layout, container, false)

        val imageView: AppCompatImageView = view.findViewById(R.id.image_view)
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)
        val rootView = view.findViewById<RelativeLayout>(R.id.root_view)

        list?.let {
            val item: Result = list[position]
            title.text = item.title
            description.text = item.overview
            Picasso.get().load(item.imageUrl()).into(imageView)

            rootView.setOnClickListener { clickItem.invoke(item) }

            container.addView(view)
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}