package com.planatech.bo.tracker.comingsoon.service

import com.planatech.bo.tracker.comingsoon.model.PagedResultsList
import retrofit2.http.GET
import retrofit2.http.Query

interface ComingSoonService {

    @GET("discover/movie")
    suspend fun getComingSoon(
        @Query("page") page: Int,
        @Query("primary_release_date.gte") startDate: String,
        @Query("primary_release_date.lte") endDate: String,
        @Query("sort_by") sortBy: String,
        @Query("with_release_type") releaseType: Int
    ): PagedResultsList

    @GET("discover/movie")
    suspend fun getComingSoonWithRegion(
        @Query("page") page: Int,
        @Query("release_date.gte") startDate: String,
        @Query("release_date.lte") endDate: String,
        @Query("sort_by") sortBy: String,
        @Query("with_release_type") releaseType: Int,
        @Query("region") region: String
    ): PagedResultsList
}