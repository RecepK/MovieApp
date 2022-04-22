package com.recep.movieapp.utils

import android.util.Log

object Constants {
    const val SLIDER_ITEM_COUNT = 5
}

fun String.mLog() {
    Log.d("MovieApp", "$this")
}