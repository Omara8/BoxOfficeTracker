package com.planatech.bo.tracker.comingsoon.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.planatech.bo.tracker.comingsoon.model.Upcoming
import com.planatech.bo.tracker.comingsoon.model.UpcomingResults
import com.planatech.bo.tracker.comingsoon.service.ComingSoonService
import com.planatech.bo.tracker.utils.extensions.formatForAPI
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class ComingSoonPagingSource @Inject constructor(val comingSoonService: ComingSoonService): PagingSource<Int, UpcomingResults>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UpcomingResults> {
        val position: Int = params.key ?: 1
        val date = Date().formatForAPI()
        return try {
            val response: Upcoming = comingSoonService.getComingSoon(date)
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

    override fun getRefreshKey(state: PagingState<Int, UpcomingResults>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}