package com.recep.movieapp.utils

import android.util.Log
import com.recep.movieapp.model.api.Result
import com.recep.sliderpager.SliderItem

object Constants {
    const val SLIDER_ITEM_COUNT = 5
}

fun String.mLog() {
    Log.d("MovieApp", "$this")
}

fun Result.toSliderItem(): SliderItem {
    return SliderItem(id, title, overview, imageUrl())
}