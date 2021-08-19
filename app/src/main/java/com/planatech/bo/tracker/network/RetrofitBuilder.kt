package com.planatech.bo.tracker.network

import com.planatech.bo.tracker.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitBuilder {

    private const val baseUrl: String = BuildConfig.SERVER_URL

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BASIC
        }

    @Singleton
    @Provides
    fun providesNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
            val defaultHeaders: MutableMap<String, String> = mutableMapOf(
                Pair("Authorization", "Bearer ${BuildConfig.API_KEY}"),
                Pair("Content-Type", "application/json")
            )
            for (header in defaultHeaders) {
                builder.addHeader(header.key, header.value)
            }

            val request = builder.build()
            chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        networkInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

}
    //the below code is used in creating the repo/api for each service
//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
//
//    @Singleton
//    @Provides
//    fun providesRepository(apiService: ApiService) = Repository(apiService)