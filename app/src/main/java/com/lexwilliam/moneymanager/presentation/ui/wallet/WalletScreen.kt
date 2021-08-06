package com.lexwilliam.moneymanager.presentation.ui.wallet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.HistoryList
import com.lexwilliam.moneymanager.presentation.ui.component.MyBarChart
import com.lexwilliam.moneymanager.presentation.ui.component.RoundedBoxContainer
import com.lexwilliam.moneymanager.presentation.ui.theme.MoneyManagerTheme
import com.lexwilliam.moneymanager.presentation.util.*

@ExperimentalPagerApi
@Composable
fun WalletScreen(
    viewModel: WalletViewModel = viewModel(),
    navToAddReport: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    var currentDate by remember { mutableStateOf(thisMonth) }
    val filterReportsByDate = viewState.wallet.reports.filter { formatDateToString(it.timeAdded!!, "MMMM yyyy") == currentDate }

    WalletContent(
        wallet = viewState.wallet,
        reports = filterReportsByDate,
        currentDate = currentDate,
        setDate = { currentDate = it },
        navToAddReport = { navToAddReport(it) },
        navToReportDetail = { navToReportDetail(it) }
    )
}

@ExperimentalPagerApi
@Composable
fun WalletContent(
    wallet: WalletPresentation,
    reports: List<ReportPresentation>,
    currentDate: String,
    setDate: (String) -> Unit,
    navToAddReport: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 64.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = { navToAddReport(wallet.name) }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            WalletTopBar(modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp), wallet = wallet)
            WalletTabRow(status = currentDate, wallet = wallet, setTime = { setDate(it) })

            val pagerState = rememberPagerState(pageCount = 2)
            HorizontalPager(state = pagerState) { page ->
                when(page) {
                    0 -> RoundedBoxContainer(modifier = Modifier.fillMaxWidth().height(240.dp).padding(horizontal = 24.dp)) {
                        WalletSummary(modifier = Modifier.padding(horizontal = 24.dp), wallet = wallet, reports = reports)
                    }

                    1 -> RoundedBoxContainer(modifier = Modifier.fillMaxWidth().height(240.dp).padding(horizontal = 24.dp)) {
                        MyBarChart(reports = reports)
                    }
                }
            }

            if(wallet.reports.isNotEmpty()) {
                Log.d("reportTAG", reports.toString())
                HistoryList(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    reports = reports,
                    navToReportDetail = { navToReportDetail(it) },
                    todayEnabled = false,
                    isWalletNameShow = false
                )
            }
        }
    }
}

@Composable
fun WalletTopBar(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .weight(2f),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = wallet.name, style = MaterialTheme.typography.h4)
        }
        Box(
            Modifier
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Menu, contentDescription = null)
            }
        }
    }
}

@Composable
fun WalletSummary(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation,
    reports: List<ReportPresentation>
) {
    var income = 0.0
    var expense = 0.0
    reports.forEach {
        when(it.reportType) {
            ReportType.Income -> income += it.money
            ReportType.Expense -> expense -= it.money
            else -> Log.d("WalletSummary", "Report Type Not Found")
        }
    }
    val total = income + expense
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            Text(text = "WALLET BALANCE", style = MaterialTheme.typography.overline)
            Text(text = convertDoubleToMoney(walletTotalBalance(wallet)), style = MaterialTheme.typography.h5)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = "Income", style = MaterialTheme.typography.overline)
                    Text(text = convertDoubleToMoney(income), style = MaterialTheme.typography.subtitle1, color = Color.Green)
                }
            }
            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = "Expense", style = MaterialTheme.typography.overline)
                    Text(text = convertDoubleToMoney(expense), style = MaterialTheme.typography.subtitle1, color = Color.Red)
                }
            }
            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = "Total", style = MaterialTheme.typography.overline)
                    Text(text = convertDoubleToMoney(total), style = MaterialTheme.typography.subtitle1)
                }
            }
        }
    }
}

@Composable
fun WalletActions(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit,
    onAnalyticsClick: () -> Unit
) {
    Row(
        modifier = modifier
            .width(360.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        @Composable
        fun WalletActionButton(
            onClick: () -> Unit,
            content: @Composable ColumnScope.() -> Unit
        ) {
            Box(
                Modifier
                    .padding(start = 24.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colors.secondary)
                    .weight(1f)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content()
                }
            }
        }
        WalletActionButton(onClick = { onAddClick() }) {
            Icon(modifier = Modifier.size(50.dp), imageVector = Icons.Default.Add, contentDescription = null)
            Text(text = "Add", style = MaterialTheme.typography.caption)
        }
        WalletActionButton(onClick = { onEditClick() }) {
            Icon(modifier = Modifier.size(50.dp), imageVector = Icons.Default.Edit, contentDescription = null)
            Text(text = "Edit", style = MaterialTheme.typography.caption)
        }
        WalletActionButton(onClick = { onAnalyticsClick() }) {
            Icon(modifier = Modifier.size(50.dp), painter = painterResource(id = R.drawable.analytics_filled_black_24dp), contentDescription = null)
            Text(text = "Analytics", style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun WalletTabRow(
    status: String,
    wallet: WalletPresentation,
    setTime: (String) -> Unit
) {
    if(wallet.reports.isNotEmpty()) {
        val dateList = configureTabRowItems(wallet)
        var currentIndex by remember { mutableStateOf(dateList.indexOf(status)) }
        ScrollableTabRow(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background,
            selectedTabIndex = currentIndex
        ) {
            dateList.forEachIndexed { index, month ->
                Tab(
                    selected = index == currentIndex,
                    onClick = {
                        currentIndex = index
                        setTime(month)
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val arr = month.split(" ")
                        Text(text = arr[0], style = MaterialTheme.typography.h6)
                        Text(text = arr[1], style = MaterialTheme.typography.overline)
                    }
                }
            }
        }
    }
}

private fun configureTabRowItems(wallet: WalletPresentation): List<String> {
    val groupedList = wallet.reports.groupBy { formatDateToString(it.timeAdded!!, "MMMM yyyy", false) }
    val onlyMonths = groupedList.map { it.key }
    val resultList = mutableListOf<String>()
    onlyMonths.forEach { month ->
        val arr = month.split(" ")
        val year = arr.last().toInt()
        val monthList = getMonthsNameFromYear(year)
        monthList.forEach {
            resultList.add(it)
        }
    }
    return resultList
}

@ExperimentalPagerApi
@Preview
@Composable
fun WalletContentPreview() {
    MoneyManagerTheme {
        WalletContent(wallet = fakeWallet, reports = fakeReports, currentDate = "", setDate = {}, navToAddReport = {}, navToReportDetail = {})
    }
}