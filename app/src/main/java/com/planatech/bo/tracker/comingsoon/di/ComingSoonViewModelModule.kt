package com.planatech.bo.tracker.comingsoon.di

import com.planatech.bo.tracker.comingsoon.repository.ComingSoonRepository
import com.planatech.bo.tracker.comingsoon.service.ComingSoonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ComingSoonViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideComingSoonRepository(comingSoonService: ComingSoonService) =
        ComingSoonRepository(comingSoonService)

    @Provides
    @ViewModelScoped
    fun provideComingSoonService(retrofit: Retrofit): ComingSoonService = retrofit.create(
        ComingSoonService::class.java)
}