package com.infoechebo.nasalibrary.di

import com.infoechebo.nasalibrary.common.Constants
import com.infoechebo.nasalibrary.data.remote.NasaMediaApi
import com.infoechebo.nasalibrary.data.repository.NasaImageRepositoryImpl
import com.infoechebo.nasalibrary.domain.repository.NasaImageRepository
import com.infoechebo.nasalibrary.domain.usecase.NasaSearch
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
    fun provideNasaMediaApi(): NasaMediaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaMediaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNasaImageRepository(api: NasaMediaApi): NasaImageRepository {
        return NasaImageRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideNasaSearchUseCase(repository: NasaImageRepository): NasaSearch {
        return NasaSearch(repository)
    }
}