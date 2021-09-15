package com.planatech.bo.tracker.trending.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.planatech.bo.tracker.comingsoon.model.Movie
import com.planatech.bo.tracker.trending.model.MediaType
import com.planatech.bo.tracker.trending.model.TimeWindow
import com.planatech.bo.tracker.trending.service.TrendingService
import com.planatech.bo.tracker.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingRepository@Inject constructor(val trendingService: TrendingService) {

    fun getTrending(mediaType: MediaType, timeWindow: TimeWindow): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TrendingPagingSource(trendingService, mediaType, timeWindow) }
        ).flow
    }

}