package com.planatech.bo.tracker.trending.service

import com.planatech.bo.tracker.comingsoon.model.PagedResultsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingService {

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrending(
        @Path("media_type") type: String,
        @Path("time_window") timeWindow: String,
        @Query("page") page: Int
    ): PagedResultsList
}