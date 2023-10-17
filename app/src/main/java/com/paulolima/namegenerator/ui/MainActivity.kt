package com.paulolima.namegenerator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.paulolima.namegenerator.feature.babyname.BabyNameScreen
import com.paulolima.namegenerator.feature.babyname.di.getBabyNameStore
import com.paulolima.namegenerator.ui.theme.NameGeneratorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameGeneratorTheme {
                BabyNameApp()
            }
        }
    }
}

@Composable
private fun BabyNameApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationKeys.Route.BABY_NAME_MAIN) {
        composable(route = NavigationKeys.Route.BABY_NAME_MAIN) {
            BabyNameScreen(getBabyNameStore(navController = navController))
        }
    }
}

object NavigationKeys {

    object Route {
        const val BABY_NAME_MAIN = "baby_name_main"
    }
}