package com.lexwilliam.moneymanager.presentation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.lexwilliam.moneymanager.HomeScreen
import com.lexwilliam.moneymanager.presentation.ui.analytic.AnalyticScreen
import com.lexwilliam.moneymanager.presentation.ui.analytic.AnalyticViewModel
import com.lexwilliam.moneymanager.presentation.ui.report.AddReportScreen
import com.lexwilliam.moneymanager.presentation.ui.report.AddReportViewModel
import com.lexwilliam.moneymanager.presentation.ui.wallet.AddWalletScreen
import com.lexwilliam.moneymanager.presentation.ui.wallet.AddWalletViewModel
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel
import com.lexwilliam.moneymanager.presentation.ui.profile.ProfileScreen
import com.lexwilliam.moneymanager.presentation.ui.profile.ProfileViewModel
import com.lexwilliam.moneymanager.presentation.ui.recurring.RecurringContent
import com.lexwilliam.moneymanager.presentation.ui.recurring.RecurringScreen
import com.lexwilliam.moneymanager.presentation.ui.recurring.RecurringViewModel
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

    data class BottomNavItem(
        val icon: ImageVector,
        val route: String,
        val description: String
    )

    val bottomNavIcons = listOf(
        BottomNavItem(Icons.Filled.Home, Screens.HomeScreen.route, "Home"),
        BottomNavItem(Icons.Filled.Search, Screens.AnalyticScreen.route, "Analytic"),
        BottomNavItem(Icons.Default.DateRange, Screens.RecurringScreen.route, "Recurring"),
        BottomNavItem(Icons.Filled.Person, Screens.ProfileScreen.route, "Profile")
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination

                bottomNavIcons.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                        label = { Text(screen.description) }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

            // WALLET CONTENT
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

            // ANALYTIC CONTENT
            // ANALYTIC SCREEN
            composable(
                route = Screens.AnalyticScreen.route
            ) {
                val analyticViewModel = hiltViewModel<AnalyticViewModel>()
                AnalyticScreen(
                    viewModel = analyticViewModel
                )
            }

            // RECURRING CONTENT
            // RECURRING SCREEN
            composable(
                route = Screens.RecurringScreen.route
            ) {
                val recurringViewModel = hiltViewModel<RecurringViewModel>()
                RecurringScreen(
                    viewModel = recurringViewModel
                )
            }

            // PROFILE CONTENT
            // PROFILE SCREEN
            composable(
                route = Screens.ProfileScreen.route
            ) {
                val profileViewModel = hiltViewModel<ProfileViewModel>()
                ProfileScreen(
                    viewModel = profileViewModel
                )
            }
        }
    }
}

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object AnalyticScreen: Screens("analytic")
    object RecurringScreen: Screens("recurring")
    object ProfileScreen: Screens("profile")
    object WalletScreen: Screens("wallet")
    object AddWalletScreen : Screens("addWallet")
    object ReportScreen: Screens("report")
    object AddReportScreen: Screens("addReport")
}