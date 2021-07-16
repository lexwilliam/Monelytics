package com.lexwilliam.moneymanager.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lexwilliam.moneymanager.HomeScreen
import com.lexwilliam.moneymanager.presentation.ui.add.AddReportScreen
import com.lexwilliam.moneymanager.presentation.ui.add.AddReportViewModel
import com.lexwilliam.moneymanager.presentation.ui.add.AddWalletScreen
import com.lexwilliam.moneymanager.presentation.ui.add.AddWalletViewModel
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel

@ExperimentalComposeUiApi
@Composable
fun MoneyManagerApp() {
    MoneyManagerContent()
}

@ExperimentalComposeUiApi
@Composable
fun MoneyManagerContent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(Screens.AppHomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = homeViewModel,
                navToReportDetail = { reportId ->
                    navController.navigate(
                        Screens.AppReportScreen.route.plus("/?reportId=$reportId")
                    )
                },
                navToWalletDetail = { walletId ->
                    navController.navigate(
                        Screens.AppWalletScreen.route.plus("/?walletId=$walletId")
                    )
                },
                navToAddWallet = {
                    navController.navigate(Screens.AppAddWalletScreen.route)
                }
            )
        }
        composable(Screens.AppAddWalletScreen.route) {
            val addWalletViewModel = hiltViewModel<AddWalletViewModel>()
            AddWalletScreen(
                viewModel = addWalletViewModel,
                navToHome = {
                    navController.navigate(Screens.AppAddWalletScreen.route)
                }
            )
        }
        composable(Screens.AppAddReportScreen.route) {
            val addReportViewModel = hiltViewModel<AddReportViewModel>()
            AddReportScreen(
                viewModel = addReportViewModel,
                navToHome = {
                    navController.navigate(Screens.AppAddWalletScreen.route)
                }
            )
        }
    }
}

sealed class Screens(val route: String) {
    object AppHomeScreen : Screens("home")
    object AppWalletScreen: Screens("wallet")
    object AppAddWalletScreen : Screens("addWallet")
    object AppReportScreen: Screens("report")
    object AppAddReportScreen: Screens("addReport")
}