package com.lexwilliam.moneymanager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navToAddWallet: () -> Unit,
    navToWalletDetail: (Int) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    HomeContent(
        wallets = viewState.wallets,
        reports = viewState.reports,
        navToAddWallet = { navToAddWallet() },
        navToWalletDetail = { navToWalletDetail(it) },
        navToReportDetail = { navToReportDetail(it) }
    )

}

@Composable
fun HomeContent(
    wallets: List<WalletPresentation>,
    reports: List<ReportPresentation>,
    navToAddWallet: () -> Unit,
    navToWalletDetail: (Int) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(9f),
                text = "Your Wallets"
            )
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { navToAddWallet() }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null )
            }
        }
        WalletCardList(wallets = wallets, navToWalletDetail = { navToWalletDetail(it) })
        HistoryList(reports = reports, navToReportDetail = { navToReportDetail(it) })
    }
}

@Composable
fun WalletCardList(
    wallets: List<WalletPresentation>,
    navToWalletDetail: (Int) -> Unit
) {
    if(wallets.isNotEmpty()) {
        Row(
            modifier = Modifier
                .scrollable(state = rememberScrollState(), orientation = Orientation.Horizontal),
        ) {
            wallets.forEach { wallet ->
                WalletCard(wallet = wallet, navToWalletDetail = { navToWalletDetail(it) })
            }
        }
    }
}

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation,
    navToWalletDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .size(width = 300.dp, height = 200.dp)
            .clickable {
                navToWalletDetail(wallet.walletId)
            }
            .shadow(elevation = 8.dp, shape = MaterialTheme.shapes.large, clip = true),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = wallet.name)
        var totalMoney = 0.0
        wallet.reports.forEach { report ->
            totalMoney += report.money
        }
        Text(text = totalMoney.toString())
    }
}

@Composable
fun HistoryList(
    reports: List<ReportPresentation>,
    navToReportDetail: (Int) -> Unit
) {
    if(reports.isNotEmpty()) {
        reports.forEach { report ->
            ReportRow(report = report, navToReportDetail = { navToReportDetail(it) })
        }
    }
}

@Composable
fun ReportRow(
    modifier: Modifier = Modifier,
    report: ReportPresentation,
    navToReportDetail: (Int) -> Unit
) {

    Row(
        modifier = modifier
            .shadow(8.dp, MaterialTheme.shapes.small, true)
            .fillMaxWidth()
            .clickable {
                navToReportDetail(report.reportId)
            }
    ) {
        Column(modifier = Modifier.weight(5f)) {
            Text(text = report.name)
            Text(text = report.timeAdded.toString())
        }
        Column(modifier = Modifier.weight(1f)) {
            when(report.reportType) {
                ReportType.Expense -> Text(text = "-${report.money}", color = Color.Red)
                ReportType.Income -> Text(text = "+${report.money}", color = Color.Green)
                ReportType.Default -> Text(text = "${report.money}")
            }
        }
    }
}