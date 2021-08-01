package com.lexwilliam.moneymanager.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.fakeReports
import com.lexwilliam.moneymanager.presentation.util.formatDateToString

//
//@Composable
//fun BarChart(
//    reports: List<ReportPresentation>
//) {
//    val groupedReports = reports.groupBy { formatDateToString(it.timeAdded!!, "MMM") }
//
//    data class MonthAndBalance(val month: String, var income: Double, var expense: Double)
//    val monthAndBalanceList = mutableListOf<MonthAndBalance>()
//    var maxBalance = 0.0
//    groupedReports.forEach { (month, reports) ->
//        val balance = MonthAndBalance(month, 0.0, 0.0)
//        reports.forEach { report ->
//            when(report.reportType) {
//                ReportType.Income -> balance.income += report.money
//                ReportType.Expense -> balance.expense -= report.money
//                else -> Log.d("BarChart", "Money Error")
//            }
//        }
//        if(balance.income > maxBalance) {
//            maxBalance = balance.income
//        }
//        if(balance.expense > maxBalance) {
//            maxBalance = -(balance.expense)
//        }
//        monthAndBalanceList.add(balance)
//    }
//
//    Column {
//        Row(
//            modifier = Modifier.height(100.dp).fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            verticalAlignment = Alignment.Bottom
//        ) {
//            monthAndBalanceList.forEach { monthAndBalance ->
//                val incomeHeightPercentage = monthAndBalance.income / maxBalance
//                val expenseHeightPercentage = monthAndBalance.expense / maxBalance
//                Row(
//                    horizontalArrangement = Arrangement.spacedBy(8.dp),
//                    verticalAlignment = Alignment.Bottom
//                ) {
//                    Box(
//                        modifier = Modifier
//                            .background(Color.Green)
//                            .fillMaxHeight(incomeHeightPercentage.toFloat())
//                            .width(24.dp)
//                    )
//                    Box(
//                        modifier = Modifier
//                            .background(Color.Red)
//                            .fillMaxHeight(expenseHeightPercentage.toFloat())
//                            .width(24.dp)
//                    )
////                    Box(
////                        modifier = Modifier
////                            .weight(1f),
////                        contentAlignment = Alignment.Center
////                    ) {
////                        Text(text = monthAndBalance.month, style = MaterialTheme.typography.subtitle2, color = Color.White)
////                    }
//                }
//            }
//        }
//    }
//}
//
//
//
//@Preview
//@Composable
//fun BarChartPreview() {
//    BarChart(reports = fakeReports)
//}
