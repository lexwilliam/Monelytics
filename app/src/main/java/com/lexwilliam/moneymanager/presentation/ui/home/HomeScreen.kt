package com.lexwilliam.moneymanager

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.HistoryList
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel
import com.lexwilliam.moneymanager.presentation.util.*


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
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HomeTopAppBar(wallets = wallets)
        WalletCardRowList(wallets = wallets, navToWalletDetail = { navToWalletDetail(it) }, navToAddWallet = { navToAddWallet()})
        HistoryList(reports = reports, navToReportDetail = { navToReportDetail(it) })
    }
}

@Composable
fun HomeTopAppBar(wallets: List<WalletPresentation>) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(48.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "Total Balance", style = MaterialTheme.typography.subtitle1)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(48.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = "$${allWalletTotalBalance(wallets)}", style = MaterialTheme.typography.h6)
            }
        }
        Divider()
    }
}

@Composable
fun WalletCardRowList(
    wallets: List<WalletPresentation>,
    navToWalletDetail: (Int) -> Unit,
    navToAddWallet: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        wallets.forEach { wallet ->
            WalletCard(modifier = Modifier.padding(end = 16.dp), wallet = wallet, navToWalletDetail = { navToWalletDetail(it) })
        }
        AddWalletCard { navToAddWallet() }
    }
}

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation,
    navToWalletDetail: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .size(width = cardWidth, height = cardHeight)
            .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
            .background(color = Color.White)
            .clickable { navToWalletDetail(wallet.walletId) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            Box(
                modifier = Modifier.size(width = cardWidth, height = cardHeight/2),
                contentAlignment = Alignment.TopStart
            ) {
                Text(text = wallet.name, style = MaterialTheme.typography.caption, color = Color.Black)
            }
            Box(
                modifier = Modifier.size(width = cardWidth, height = cardHeight/2),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(text = "$${walletTotalBalance(wallet)}", color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun AddWalletCard(
    navToAddWallet: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = cardWidth, height = cardHeight)
            .padding(end = 16.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
            .background(color = MaterialTheme.colors.secondaryVariant)
            .clickable { navToAddWallet() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun WalletCardPreview() {
    WalletCard(
        wallet = WalletPresentation(name = "Test", reports = emptyList()),
        navToWalletDetail = {}
    )
}