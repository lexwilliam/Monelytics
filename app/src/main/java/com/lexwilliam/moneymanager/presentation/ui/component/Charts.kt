package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.convertLongToTime
import com.lexwilliam.moneymanager.presentation.util.fakeReports

@Composable
fun BarChart(
    reports: List<ReportPresentation>
) {
    val groupedReports = reports.groupBy { convertLongToTime(it.timeAdded, "MMM") }

    Column {
        Row {
            data class MonthAndBalance(val month: String, var balance: Double)
            val monthAndBalanceList = mutableListOf<MonthAndBalance>()
            var maxBalance = MonthAndBalance("", 0.0)
            var minBalance = MonthAndBalance("", 0.0)
            groupedReports.forEach { (month, reports) ->
                var balance = 0.0
                reports.forEach { report ->
                    balance += report.money
                }
                if(balance > maxBalance.balance) {
                    maxBalance.balance = balance
                }
                if(balance < minBalance.balance) {
                    minBalance.balance = balance
                }
                monthAndBalanceList.add(MonthAndBalance(month, balance))
            }
            monthAndBalanceList.forEach { monthAndBalance ->
                val balancePercentage = monthAndBalance.balance / maxBalance.balance
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxHeight(-balancePercentage.toFloat())
                            .width(48.dp)
                            .weight(9f)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = monthAndBalance.month, style = MaterialTheme.typography.subtitle2, color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun BarChartPreview() {
    BarChart(reports = fakeReports)
}
