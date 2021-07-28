package com.lexwilliam.moneymanager.presentation.ui.wallet

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportCategory
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.HistoryList
import com.lexwilliam.moneymanager.presentation.ui.report.CategoryRow
import com.lexwilliam.moneymanager.presentation.ui.report.CategoryTabRow
import com.lexwilliam.moneymanager.presentation.ui.theme.MoneyManagerTheme
import com.lexwilliam.moneymanager.presentation.util.*

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
                    .shadow(4.dp, RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp), true)
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
                    WalletSummary(modifier = Modifier.padding(bottom = 48.dp, start = 24.dp, end = 24.dp), wallet = wallet)
                }
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                var status by remember { mutableStateOf(thisMonth) }
                WalletTabRow(status = status, wallet = wallet, setTime = { status = it })
                HistoryList(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    reports = wallet.reports,
                    navToReportDetail = { navToReportDetail(it) },
                    todayEnabled = false
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
            Text(text = "Insights", style = MaterialTheme.typography.h4, color = Color.White)
        }
        Box(
            Modifier
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.Settings, contentDescription = null, tint = Color.White)
            }
        }
    }
}

@Composable
fun WalletSummary(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column {
            Text(text = "TOTAL BALANCE", style = MaterialTheme.typography.overline, color = MaterialTheme.colors.secondary)
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
                    Text(text = convertDoubleToMoney(getWalletIncome(wallet)), style = MaterialTheme.typography.subtitle1, color = Color.Green)
                }
            }
            Box(
                Modifier
                    .weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = "Expense", style = MaterialTheme.typography.overline, color = MaterialTheme.colors.secondary)
                    Text(text = convertDoubleToMoney(getWalletExpense(wallet)), style = MaterialTheme.typography.subtitle1, color = Color.Red)
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
    val fakeMonthList = listOf("April 2021", "May 2021", "June 2021", "July 2021")
    val groupedList = wallet.reports.groupBy { convertLongToTime(it.timeAdded, "MMMM yyyy", false) }
    val onlyMonths = groupedList.map { it.key }
    if(fakeMonthList.isNotEmpty()) {
    var currentIndex by remember { mutableStateOf(fakeMonthList.indexOf(status)) }
        ScrollableTabRow(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.background,
            selectedTabIndex = currentIndex
        ) {
            fakeMonthList.forEachIndexed { index, month ->
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

@Preview
@Composable
fun WalletContentPreview() {
    MoneyManagerTheme {
        WalletContent(wallet = fakeWallet, navToAddReport = {}, navToReportDetail = {})
    }
}