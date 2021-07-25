package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.lexwilliam.moneymanager.presentation.util.convertDoubleToMoneyFormat
import com.lexwilliam.moneymanager.presentation.util.convertLongToTime
import com.lexwilliam.moneymanager.presentation.util.convertStringToColor

@Composable
fun HistoryList(
    modifier: Modifier = Modifier,
    reports: List<ReportPresentation>,
    navToReportDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val groupedReports = reports.groupBy { convertLongToTime(it.timeAdded, "EEE, dd MMM yyyy") }
        groupedReports.forEach { (time, groupOfReport) ->
            Text(text = time, style = MaterialTheme.typography.subtitle1, color = Color.LightGray)
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(convertStringToColor(report.color))
            )
            Column {
                Text(text = report.name, style = MaterialTheme.typography.subtitle1)
                Text(text = report.walletName, style = MaterialTheme.typography.overline)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = convertDoubleToMoneyFormat(report.money))
        }
    }
}