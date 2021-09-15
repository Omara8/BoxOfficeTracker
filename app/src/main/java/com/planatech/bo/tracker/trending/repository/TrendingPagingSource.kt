package com.planatech.bo.tracker.trending.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.planatech.bo.tracker.comingsoon.model.Movie
import com.planatech.bo.tracker.comingsoon.model.PagedResultsList
import com.planatech.bo.tracker.trending.model.MediaType
import com.planatech.bo.tracker.trending.model.TimeWindow
import com.planatech.bo.tracker.trending.service.TrendingService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrendingPagingSource @Inject constructor(
    val trendingService: TrendingService,
    val mediaType: MediaType,
    val timeWindow: TimeWindow
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position: Int = params.key ?: 1
        return try {
            val response: PagedResultsList =
                trendingService.getTrending(mediaType.type, timeWindow.window, position)
            val items = response.results
            val nextKey = if (items.isEmpty()) {
                null
            } else {
                position + 1
            }
            LoadResult.Page(
                data = items,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}