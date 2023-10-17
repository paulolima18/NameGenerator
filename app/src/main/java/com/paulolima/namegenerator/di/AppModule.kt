package com.paulolima.namegenerator.di

import android.content.Context
import com.paulolima.namegenerator.service.DispatcherService
import com.paulolima.namegenerator.service.DispatcherServiceInterface
import com.paulolima.namegenerator.ui.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    fun providesDispatcherProvider(): DispatcherServiceInterface = DispatcherService()
}