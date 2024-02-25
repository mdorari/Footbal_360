package com.example.footbal360.di

import com.example.footbal360.data.FootballApi
import com.example.footbal360.data.FootballRepository
import com.example.footbal360.data.FootballRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMyApi():FootballApi{
        return Retrofit.Builder()
            .baseUrl(FootballApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepository(api: FootballApi):FootballRepository{
        return FootballRepositoryImpl(api)
    }
}