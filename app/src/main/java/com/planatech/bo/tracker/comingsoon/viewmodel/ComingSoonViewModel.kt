package com.planatech.bo.tracker.comingsoon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.planatech.bo.tracker.comingsoon.model.Movie
import com.planatech.bo.tracker.comingsoon.repository.ComingSoonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ComingSoonViewModel @Inject constructor(
    val comingSoonRepository: ComingSoonRepository
) : ViewModel() {

    fun getComingSoon(isUSOnly: Boolean): Flow<PagingData<Movie>> {
        return comingSoonRepository.getComingSoon(isUSOnly)
            .cachedIn(viewModelScope)
    }

}
