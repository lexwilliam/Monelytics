package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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
            .background(color = MaterialTheme.colors.secondaryVariant)
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