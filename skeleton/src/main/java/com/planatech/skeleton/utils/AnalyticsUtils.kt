package com.planatech.skeleton.utils

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsUtils {

    @Singleton
    @Provides
    fun providesFirebaseAnalytics(@ApplicationContext context: Context) =
        FirebaseAnalytics.getInstance(context)

    fun logEvent(firebaseAnalytics: FirebaseAnalytics, event: AnalyticsEvents) {
        firebaseAnalytics.logEvent(event.value) {
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "event")
        }
    }
}

enum class AnalyticsEvents(val value: String) {
    OpenedApp("OpenedApp")
}