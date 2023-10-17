package com.paulolima.namegenerator.feature.babyname.di

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.paulolima.namegenerator.feature.babyname.BabyNameStore
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface BabyNameStoreFactoryProvider {
    fun babyNameStoreFactory(): BabyNameStoreFactory
}

@AssistedFactory
interface BabyNameStoreFactory {
    fun createStore(
        @Assisted
        navController: NavController
    ): BabyNameStore
}

@Composable
fun getBabyNameStore(
    navController: NavController,
): BabyNameStore {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        BabyNameStoreFactoryProvider::class.java
    )

    return viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.babyNameStoreFactory().createStore(
                    navController = navController
                ) as T
            }
        }
    )
}