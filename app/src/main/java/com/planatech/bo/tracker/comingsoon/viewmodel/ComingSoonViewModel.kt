package com.planatech.bo.tracker.comingsoon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.planatech.bo.tracker.comingsoon.model.UpcomingResults
import com.planatech.bo.tracker.comingsoon.repository.ComingSoonRepository
import com.planatech.bo.tracker.utils.extensions.formatForAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(
    val comingSoonRepository: ComingSoonRepository
) : ViewModel() {

    fun getComingSoon(isUSOnly: Boolean): Flow<PagingData<UpcomingResults>> {
        return comingSoonRepository.getComingSoon(isUSOnly)
            .cachedIn(viewModelScope)
    }

}
