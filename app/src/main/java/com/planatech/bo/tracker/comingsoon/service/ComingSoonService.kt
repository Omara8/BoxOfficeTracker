package com.planatech.bo.tracker.comingsoon.service

import com.planatech.bo.tracker.comingsoon.model.Upcoming
import retrofit2.http.GET
import retrofit2.http.Query

interface ComingSoonService {

    @GET("discover/movie")
    suspend fun getComingSoon(@Query("release_date_gte") release_date_gte: String): Upcoming
}