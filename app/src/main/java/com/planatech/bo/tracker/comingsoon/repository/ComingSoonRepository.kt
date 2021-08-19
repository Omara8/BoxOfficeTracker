package com.planatech.bo.tracker.comingsoon.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.planatech.bo.tracker.comingsoon.model.UpcomingResults
import com.planatech.bo.tracker.comingsoon.service.ComingSoonService
import com.planatech.bo.tracker.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComingSoonRepository @Inject constructor(val comingSoonService: ComingSoonService) {

    fun getComingSoon(): Flow<PagingData<UpcomingResults>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ComingSoonPagingSource(comingSoonService) }
        ).flow
    }

}