package com.lexwilliam.moneymanager.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.lexwilliam.moneymanager.HomeScreen
import com.lexwilliam.moneymanager.presentation.ui.add.AddReportScreen
import com.lexwilliam.moneymanager.presentation.ui.add.AddReportViewModel
import com.lexwilliam.moneymanager.presentation.ui.add.AddWalletScreen
import com.lexwilliam.moneymanager.presentation.ui.add.AddWalletViewModel
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel
import com.lexwilliam.moneymanager.presentation.ui.report.ReportScreen
import com.lexwilliam.moneymanager.presentation.ui.report.ReportViewModel
import com.lexwilliam.moneymanager.presentation.ui.wallet.WalletScreen
import com.lexwilliam.moneymanager.presentation.ui.wallet.WalletViewModel

@ExperimentalComposeUiApi
@Composable
fun MoneyManagerApp() {
    MoneyManagerContent()
}

@ExperimentalComposeUiApi
@Composable
fun MoneyManagerContent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

        // HOME SCREEN
        composable(Screens.HomeScreen.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = homeViewModel,
                navToReportDetail = { report_id ->
                    navController.navigate(
                        Screens.ReportScreen.route.plus("/?report_id=$report_id")
                    )
                },
                navToWalletDetail = { wallet_id ->
                    navController.navigate(
                        Screens.WalletScreen.route.plus("/?wallet_id=$wallet_id")
                    )
                },
                navToAddWallet = {
                    navController.navigate(Screens.AddWalletScreen.route)
                }
            )
        }

        // WALLET SCREEN
        composable(
            route = Screens.WalletScreen.route.plus("/?wallet_id={wallet_id}"),
            arguments = listOf(
                navArgument("wallet_id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val walletViewModel = hiltViewModel<WalletViewModel>()
            WalletScreen(
                viewModel = walletViewModel,
                navToAddReport = { wallet_id ->
                    navController.navigate(Screens.AddReportScreen.route.plus("/?wallet_id=$wallet_id"))
                },
                navToReportDetail = { report_id ->
                    navController.navigate(Screens.ReportScreen.route.plus("/?report_id=$report_id"))
                }
            )
        }

        // ADD WALLET SCREEN
        composable(Screens.AddWalletScreen.route) {
            val addWalletViewModel = hiltViewModel<AddWalletViewModel>()
            AddWalletScreen(
                viewModel = addWalletViewModel,
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }

        // REPORT SCREEN
        composable(
            route = Screens.ReportScreen.route.plus("/?report_id={report_id}"),
            arguments = listOf(
                navArgument("report_id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val reportViewModel = hiltViewModel<ReportViewModel>()
            ReportScreen(
                viewModel = reportViewModel,
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }

        // ADD REPORT SCREEN
        composable(
            route = Screens.AddReportScreen.route.plus("/?wallet_id={wallet_id}"),
            arguments = listOf(
                navArgument("wallet_id") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val addReportViewModel = hiltViewModel<AddReportViewModel>()
            AddReportScreen(
                viewModel = addReportViewModel,
                onBackPressed = {
                    navController.navigateUp()
                }
            )
        }
    }
}

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object WalletScreen: Screens("wallet")
    object AddWalletScreen : Screens("addWallet")
    object ReportScreen: Screens("report")
    object AddReportScreen: Screens("addReport")
}