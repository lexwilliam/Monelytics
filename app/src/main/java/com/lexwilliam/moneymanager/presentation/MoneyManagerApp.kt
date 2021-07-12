package com.lexwilliam.moneymanager.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lexwilliam.moneymanager.HomeScreen
import com.lexwilliam.moneymanager.presentation.Screens.*
import com.lexwilliam.moneymanager.presentation.ui.add.AddViewModel
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel

@Composable
fun MoneyManagerApp() {
    MoneyManagerContent()
}

@Composable
fun MoneyManagerContent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(AppHomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(viewModel = homeViewModel)
        }
        composable(AppAddScreen.route) {

        }
    }
}

sealed class Screens(val route: String) {
    object AppHomeScreen : Screens("home")
    object AppAddScreen : Screens("add")
}