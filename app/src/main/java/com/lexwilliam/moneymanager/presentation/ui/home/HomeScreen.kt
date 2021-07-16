package com.lexwilliam.moneymanager

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel
import com.lexwilliam.moneymanager.presentation.util.allWalletTotalBalance
import com.lexwilliam.moneymanager.presentation.util.convertLongToTime
import com.lexwilliam.moneymanager.presentation.util.walletTotalBalance


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
                    .fillMaxWidth(1f)
                    .height(48.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "Total Balance", style = MaterialTheme.typography.subtitle1)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
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
        Spacer(modifier = Modifier.padding(16.dp))
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
            .size(width = 135.dp, height = 150.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
            .background(color = MaterialTheme.colors.primaryVariant)
            .clickable { navToWalletDetail(wallet.walletId) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .size(width = 125.dp, height = 140.dp)
                .background(color = MaterialTheme.colors.primary)
                .clip(MaterialTheme.shapes.medium)
        ) {
            Box(
                modifier = Modifier.size(width = 125.dp, height = 70.dp).padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(text = wallet.name, style = MaterialTheme.typography.caption, color = Color.White)
            }
            Box(
                modifier = Modifier.size(width = 125.dp, height = 70.dp).padding(16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(text = "$${walletTotalBalance(wallet)}", color = Color.White)
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
            .size(width = 135.dp, height = 150.dp)
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

@Composable
fun HistoryList(
    reports: List<ReportPresentation>,
    navToReportDetail: (Int) -> Unit
) {
    if(reports.isNotEmpty()) {
        val groupedReports = reports.groupBy { convertLongToTime(it.timeAdded) }
        groupedReports.keys.forEach { time ->
            Text(text = time, style = MaterialTheme.typography.subtitle1)
            groupedReports.values.forEach { groupOfReport ->
                groupOfReport.forEach { report ->
                    ReportRow(report = report) {
                        navToReportDetail(it)
                    }
                }
            }
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
            .fillMaxWidth()
            .clickable {
                navToReportDetail(report.reportId)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            Text(text = report.name, style = MaterialTheme.typography.body2)
            Text(text = report.thisWalletId.toString(), style = MaterialTheme.typography.overline)
        }
        when(report.reportType) {
            ReportType.Income -> Text(text = "+${report.money}")
            ReportType.Expense -> Text(text = "-${report.money}")
            ReportType.Default -> Text(text = "?${report.money}")
        }
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