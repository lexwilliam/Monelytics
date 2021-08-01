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
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.HistoryList
import com.lexwilliam.moneymanager.presentation.ui.theme.MoneyManagerTheme
import com.lexwilliam.moneymanager.presentation.util.*

@Composable
fun WalletScreen(
    viewModel: WalletViewModel = viewModel(),
    navToAddReport: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    var currentDate by remember { mutableStateOf(thisMonth) }
    Log.d("TAG", currentDate)
    WalletContent(
        wallet = viewState.wallet,
        currentDate = currentDate,
        setDate = { currentDate = it },
        navToAddReport = { navToAddReport(it) },
        navToReportDetail = { navToReportDetail(it) }
    )
}

@Composable
fun WalletContent(
    wallet: WalletPresentation,
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
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Column(
                    Modifier.background(MaterialTheme.colors.primary),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    WalletTopBar(modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp), wallet = wallet)
                    Column(Modifier.padding(horizontal = 24.dp)) {
                        Row(
                            modifier = Modifier
                                .clip(MaterialTheme.shapes.medium)
                                .fillMaxWidth()
                                .padding(1.dp)
                                .background(MaterialTheme.colors.secondary),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(painter = painterResource(id = wallet.iconId), contentDescription = null)
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = wallet.name, style = MaterialTheme.typography.subtitle1)
                            Spacer(modifier = Modifier.padding(4.dp))
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    }
                    WalletSummary(modifier = Modifier.padding(bottom = 24.dp, start = 24.dp, end = 24.dp), wallet = wallet)
                }
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                WalletTabRow(status = currentDate, wallet = wallet, setTime = { setDate(it) })
                if(wallet.reports.isNotEmpty()) {
                    val reports = wallet.reports.filter { formatDateToString(it.timeAdded!!, "MMMM yyyy", false) == currentDate }
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
            Text(text = "Insights", style = MaterialTheme.typography.h4, color = Color.White)
        }
        Box(
            Modifier
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Menu, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Composable
fun WalletSummary(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation
) {
    val income = getWalletIncome(wallet)
    val expense = getWalletExpense(wallet)
    val total = income + expense
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            Text(text = "WALLET BALANCE", style = MaterialTheme.typography.overline, color = MaterialTheme.colors.secondary)
            Text(text = convertDoubleToMoney(walletTotalBalance(wallet)), style = MaterialTheme.typography.h5, color = Color.White)
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
                    Text(text = "Income", style = MaterialTheme.typography.overline, color = MaterialTheme.colors.secondary)
                    Text(text = convertDoubleToMoney(income), style = MaterialTheme.typography.subtitle1, color = Color.Green)
                }
            }
            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = "Expense", style = MaterialTheme.typography.overline, color = MaterialTheme.colors.secondary)
                    Text(text = convertDoubleToMoney(expense), style = MaterialTheme.typography.subtitle1, color = Color.Red)
                }
            }
            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = "Total", style = MaterialTheme.typography.overline, color = MaterialTheme.colors.secondary)
                    Text(text = convertDoubleToMoney(total), style = MaterialTheme.typography.subtitle1, color = Color.White)
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

fun configureTabRowItems(wallet: WalletPresentation): List<String> {
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

@Preview
@Composable
fun WalletContentPreview() {
    MoneyManagerTheme {
        WalletContent(wallet = fakeWallet, currentDate = "", setDate = {}, navToAddReport = {}, navToReportDetail = {})
    }
}