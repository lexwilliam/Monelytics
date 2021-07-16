package com.lexwilliam.moneymanager.presentation.ui.wallet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.util.walletTotalBalance

@Composable
fun WalletScreen(
    viewModel: WalletViewModel = viewModel(),
    navToAddReport: () -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    WalletContent(
        wallet = viewState.wallet,
        navToAddReport = { navToAddReport() },
        navToReportDetail = { navToReportDetail(it) }
    )
}

@Composable
fun WalletContent(
    wallet: WalletPresentation,
    navToAddReport: () -> Unit,
    navToReportDetail: (Int) -> Unit
) {

}

@Composable
fun WalletTopAppBar(
    wallet: WalletPresentation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(1f).height(64.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = wallet.name)
        }
        Box(
            modifier = Modifier.fillMaxWidth(1f).height(64.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = "$${walletTotalBalance(wallet)}")
        }
    }
}