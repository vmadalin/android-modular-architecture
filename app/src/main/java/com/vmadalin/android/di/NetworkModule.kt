package com.vmadalin.android.di

import com.vmadalin.android.BuildConfig
import com.vmadalin.android.di.scopes.AppScope
import com.vmadalin.android.network.MarvelService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideMarvelService(): MarvelService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelService::class.java)
    }
}
