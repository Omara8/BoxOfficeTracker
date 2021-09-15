package com.planatech.bo.tracker.trending.di

import com.planatech.bo.tracker.trending.repository.TrendingRepository
import com.planatech.bo.tracker.trending.service.TrendingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object TrendingViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideTrendingRepository(trendingService: TrendingService) =
        TrendingRepository(trendingService)

    @Provides
    @ViewModelScoped
    fun provideTrendingService(retrofit: Retrofit): TrendingService = retrofit.create(
        TrendingService::class.java)

}