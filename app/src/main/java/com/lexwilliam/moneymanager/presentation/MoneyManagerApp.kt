package com.lexwilliam.moneymanager.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.lexwilliam.moneymanager.HomeScreen
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel
import com.lexwilliam.moneymanager.presentation.ui.profile.ProfileScreen
import com.lexwilliam.moneymanager.presentation.ui.profile.ProfileViewModel
import com.lexwilliam.moneymanager.presentation.ui.recurring.RecurringScreen
import com.lexwilliam.moneymanager.presentation.ui.recurring.RecurringViewModel
import com.lexwilliam.moneymanager.presentation.ui.report.*
import com.lexwilliam.moneymanager.presentation.ui.wallet.AddWalletScreen
import com.lexwilliam.moneymanager.presentation.ui.wallet.AddWalletViewModel
import com.lexwilliam.moneymanager.presentation.ui.wallet.WalletScreen
import com.lexwilliam.moneymanager.presentation.ui.wallet.WalletViewModel
import com.mikepenz.iconics.compose.ExperimentalIconics

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun MoneyManagerApp(
    email: String?,
    userName: String?,
    userPhoto: String?
) {
    MoneyManagerContent(
        email = email,
        userName = userName,
        userPhoto = userPhoto
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun MoneyManagerContent(
    email: String?,
    userName: String?,
    userPhoto: String?
) {
    val navController = rememberNavController()

    data class BottomNavItem(
        val iconOutlined: Int,
        val iconFilled: Int,
        val route: String,
        val description: String
    )

    val bottomNavIcons = listOf(
        BottomNavItem(R.drawable.account_filled_balance_wallet_black_24dp, R.drawable.account_balance_wallet_black_24dp, Screens.HomeScreen.route, "Home"),
        BottomNavItem(R.drawable.subscriptions_filled_black_24dp, R.drawable.subscriptions_black_24dp, Screens.RecurringScreen.route, "Recurring"),
        BottomNavItem(R.drawable.settings_filled_black_24dp, R.drawable.settings_black_24dp, Screens.ProfileScreen.route, "Profile")
    )

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.primary
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination

                bottomNavIcons.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            if(currentRoute?.hierarchy?.any { it.route == screen.route } == true) {
                                Icon(painter = painterResource(id = screen.iconOutlined), contentDescription = null, tint = MaterialTheme.colors.secondary)
                            } else {
                                Icon(painter = painterResource(id = screen.iconFilled), contentDescription = null, tint = Color.LightGray)
                            }

                        },
                        selected = currentRoute?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
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
                    userName = userName,
                    userPhoto = userPhoto,
                    navToReportDetail = { report_id ->
                        navController.navigate(
                            Screens.ReportScreen.route.plus("/?report_id=$report_id")
                        )
                    },
                    navToWalletDetail = { wallet_name ->
                        navController.navigate(
                            Screens.WalletScreen.route.plus("/?wallet_name=$wallet_name")
                        )
                    },
                    navToAddWallet = {
                        navController.navigate(Screens.AddWalletScreen.route)
                    }
                )
            }

            // WALLET SCREEN
            composable(
                route = Screens.WalletScreen.route.plus("/?wallet_name={wallet_name}"),
                arguments = listOf(
                    navArgument("wallet_name") {
                        type = NavType.StringType
                        defaultValue = ""
                    }
                )
            ) {
                val walletViewModel = hiltViewModel<WalletViewModel>()
                WalletScreen(
                    viewModel = walletViewModel,
                    navToAddReport = { wallet_name ->
                        navController.navigate(Screens.AddReportScreen.route.plus("/?wallet_name=$wallet_name"))
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
                route = Screens.AddReportScreen.route
                    .plus("/?wallet_name={wallet_name}"),
                arguments = listOf(
                    navArgument("wallet_name") {
                        type = NavType.StringType
                        defaultValue = ""
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
    object RecurringScreen: Screens("recurring")
    object ProfileScreen: Screens("profile")
    object WalletScreen: Screens("wallet")
    object AddWalletScreen : Screens("addWallet")
    object ReportScreen: Screens("report")
    object AddReportScreen: Screens("addReport")
}