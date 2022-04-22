package com.recep.movieapp.model.api

import android.os.Parcelable
import com.recep.movieapp.data.network.ApiConstants
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable {
    fun imageUrl() = "${ApiConstants.POSTER_URL}${poster_path}"
}