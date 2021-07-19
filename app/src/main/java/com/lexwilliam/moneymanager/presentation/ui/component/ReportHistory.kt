package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.convertLongToTime

@Composable
fun HistoryList(
    reports: List<ReportPresentation>,
    navToReportDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Recent Transactions", style = MaterialTheme.typography.h6)
        val groupedReports = reports.groupBy { convertLongToTime(it.timeAdded, "EEE, dd MMM yyyy") }
        groupedReports.forEach { (time, groupOfReport) ->
            Text(text = time, style = MaterialTheme.typography.subtitle2)
            groupOfReport.forEach { report ->
                ReportRow(report = report) {
                    navToReportDetail(it)
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
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
            ) {
                Text(text = report.name, style = MaterialTheme.typography.subtitle1)
                Text(text = report.thisWalletId.toString(), style = MaterialTheme.typography.overline)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            when(report.reportType) {
                ReportType.Income -> Text(text = "+${report.money}")
                ReportType.Expense -> Text(text = "-${report.money}")
                ReportType.Default -> Text(text = "?${report.money}")
            }
        }
    }
}