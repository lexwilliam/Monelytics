package com.lexwilliam.moneymanager

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.HistoryList
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel
import com.lexwilliam.moneymanager.presentation.util.*


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    navToAddWallet: () -> Unit,
    navToWalletDetail: (String) -> Unit,
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
    navToWalletDetail: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HomeTopAppBar()
        TotalBalance(wallets = wallets)
        WalletCardRowList(wallets = wallets, navToWalletDetail = { navToWalletDetail(it) }, navToAddWallet = { navToAddWallet()})
        HistoryList(modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 54.dp), reports = reports, navToReportDetail = { navToReportDetail(it) })
    }
}

@Composable
fun HomeTopAppBar() {
    TopAppBar(
        title = { Text(text = "")},
        navigationIcon = {
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(Icons.Default.Menu, contentDescription = null, tint = Color.Gray)
            }
        },
        actions = {
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(Icons.Default.Settings, contentDescription = null, tint = Color.Gray)
            }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    )
}

@Composable
fun TotalBalance(
    wallets: List<WalletPresentation>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "TOTAL BALANCE", style = MaterialTheme.typography.overline, color = Color.LightGray)
        Text(text = convertDoubleToMoneyFormat(allWalletTotalBalance(wallets)), style = MaterialTheme.typography.h3)
    }
}

@Composable
fun WalletCardRowList(
    wallets: List<WalletPresentation>,
    navToWalletDetail: (String) -> Unit,
    navToAddWallet: () -> Unit
) {
    if(wallets.isEmpty()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(cardHeight)
                .fillMaxWidth()
                .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
                .background(color = Color.White)
                .clickable { navToAddWallet() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Add Wallet", style = MaterialTheme.typography.h4)
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            wallets.forEach { wallet ->
                WalletCard(wallet = wallet, navToWalletDetail = { navToWalletDetail(it) })
            }
            AddWalletCard { navToAddWallet() }
        }
    }

}

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation,
    navToWalletDetail: (String) -> Unit
) {
    Column(
        modifier = modifier
            .width(cardWidth)
            .wrapContentHeight()
            .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
            .background(color = Color.White)
            .clickable { navToWalletDetail(wallet.name) }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = wallet.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.padding(36.dp))
            Text(text = "Balance", style = MaterialTheme.typography.caption)
            Text(text = convertDoubleToMoneyFormat(walletTotalBalance(wallet)), style = MaterialTheme.typography.h5)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.AccountBox, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Edit, contentDescription = null)
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
            .size(width = 100.dp, height = 100.dp)
            .padding(end = 24.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
            .background(color = MaterialTheme.colors.secondaryVariant)
            .clickable { navToAddWallet() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
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