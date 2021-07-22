package com.lexwilliam.moneymanager.presentation.ui.wallet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.HistoryList
import com.lexwilliam.moneymanager.presentation.util.convertDoubleToMoneyFormat
import com.lexwilliam.moneymanager.presentation.util.walletTotalBalance

@Composable
fun WalletScreen(
    viewModel: WalletViewModel = viewModel(),
    navToAddReport: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    WalletContent(
        wallet = viewState.wallet,
        navToAddReport = { navToAddReport(it) },
        navToReportDetail = { navToReportDetail(it) }
    )
}

@Composable
fun WalletContent(
    wallet: WalletPresentation,
    navToAddReport: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        WalletTopAppBar(wallet)
        IncomeAndExpenseCard(wallet = wallet)
        HistoryList(
            reports = wallet.reports,
            navToReportDetail = { navToReportDetail(it) }
        )
        FloatingActionButton(onClick = { navToAddReport(wallet.name) }) {
            Icon(Icons.Default.Add, contentDescription = null )
        }
    }
}

@Composable
fun WalletTopAppBar(
    wallet: WalletPresentation
) {
    TopAppBar(
        title = {
            Column {
                Text(text = wallet.name, style = MaterialTheme.typography.caption, color = Color.LightGray)
                Text(text = convertDoubleToMoneyFormat(walletTotalBalance(wallet)))
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painterResource(id = R.drawable.date_range_black_24dp), contentDescription = null)
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    )
}

@Composable
fun IncomeAndExpenseCard(
    wallet: WalletPresentation
) {
    val reports = wallet.reports
    var expense = 0.0
    var income = 0.0
    reports.forEach { report ->
        when(report.reportType) {
            ReportType.Expense -> expense -= report.money
            ReportType.Income -> income += report.money
            ReportType.Default -> Log.d("TAG", "No Report Type")
        }
    }
    val total = income + expense
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(

            modifier = Modifier.fillMaxWidth(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Inflow  : $income")
                    Text(text = "Outflow : $expense")
                    Divider()
                    Text(text = "Total   : $total")
            }
        }
        Box(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            Text("Pie Chart")
        }
    }
}