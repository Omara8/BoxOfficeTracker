package com.planatech.bo.tracker.trending.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.planatech.bo.tracker.comingsoon.model.Movie
import com.planatech.bo.tracker.trending.model.MediaType
import com.planatech.bo.tracker.trending.model.TimeWindow
import com.planatech.bo.tracker.trending.repository.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(val trendingRepository: TrendingRepository) :
    ViewModel() {

    fun getTrending(): Flow<PagingData<Movie>> {
        return trendingRepository.getTrending(MediaType.MOVIE, TimeWindow.WEEK)
    }

}