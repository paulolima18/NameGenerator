package com.paulolima.namegenerator.di

import com.paulolima.namegenerator.network.BabyNameService
import com.paulolima.namegenerator.repository.BabyNameRepository
import com.paulolima.namegenerator.repository.BabyNameRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBabyNameRepository(
        recipeService: BabyNameService,
    ): BabyNameRepositoryInterface {
        return BabyNameRepository(
            babyNameService = recipeService
        )
    }
}