package com.paulolima.namegenerator.di

import com.google.gson.GsonBuilder
import com.paulolima.namegenerator.network.BabyNameService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBabyNameService(): BabyNameService {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/paulolima18/fc1ad8843ceedf3b17fae10ec7e9a4cf/raw/63c5c6a7e4256fadea852202d4230caf9a9b196a/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(BabyNameService::class.java)
    }
}