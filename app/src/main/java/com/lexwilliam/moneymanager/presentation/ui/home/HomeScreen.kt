package com.lexwilliam.moneymanager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val viewState by viewModel.state.collectAsState()
    var currentWallet by remember { mutableStateOf(WalletPresentation(name = "", reports = emptyList())) }
    HomeContent(
        wallets = viewState.reports,
        currentWallet = currentWallet,
        setCurrentWallet = { currentWallet = it }
    )

}

@Composable
fun HomeContent(
    wallets: List<WalletPresentation>,
    currentWallet: WalletPresentation,
    setCurrentWallet: (WalletPresentation) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        WalletCardList(wallets = wallets, setCurrentWallet = { setCurrentWallet(it) })
        HistoryList(wallet = currentWallet)
    }
}

@Composable
fun WalletCardList(
    wallets: List<WalletPresentation>,
    setCurrentWallet: (WalletPresentation) -> Unit
) {
    Row(
        modifier = Modifier
            .scrollable(state = rememberScrollState(), orientation = Orientation.Horizontal),
    ) {
        wallets.forEach { wallet ->
            WalletCard(wallet = wallet, setCurrentWallet = { setCurrentWallet(it) })
        }
    }
}

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation,
    setCurrentWallet: (WalletPresentation) -> Unit
) {
    Column(
        modifier = modifier
            .size(width = 300.dp, height = 200.dp)
            .clickable {
                setCurrentWallet(wallet)
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
    wallet: WalletPresentation
) {
    wallet.reports.forEach { report ->
        ReportRow(report = report)
    }
}

@Composable
fun ReportRow(
    modifier: Modifier = Modifier,
    report: Report
) {

    Row(
        modifier = modifier
            .shadow(8.dp, MaterialTheme.shapes.small, true)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.weight(5f)) {
            Text(text = report.name)
            Text(text = report.timeAdded.toString())
        }
        Column(modifier = Modifier.weight(1f)) {
            when(report.reportType) {
                ReportType.Expense -> Text(text = "-${report.money}", color = Color.Red)
                ReportType.Income -> Text(text = "+${report.money}", color = Color.Green)
            }
        }
    }
}