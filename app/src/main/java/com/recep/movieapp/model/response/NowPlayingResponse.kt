package com.recep.movieapp.model.response

import com.recep.movieapp.model.api.Dates
import com.recep.movieapp.model.api.Result
import retrofit2.Response

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)