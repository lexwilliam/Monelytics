package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.convertDoubleToMoney
import com.lexwilliam.moneymanager.presentation.util.convertLongToTime
import com.lexwilliam.moneymanager.presentation.util.convertStringToColor
import com.lexwilliam.moneymanager.presentation.util.fakeReports

@Composable
fun HistoryList(
    modifier: Modifier = Modifier,
    reports: List<ReportPresentation>,
    todayEnabled: Boolean = true,
    isWalletNameShow: Boolean = true,
    navToReportDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val sortedReport = reports.sortedByDescending { it.timeAdded }
        val groupedReports = sortedReport.groupBy { convertLongToTime(it.timeAdded, "EEE, dd MMM yyyy", todayYesterday = todayEnabled) }
        groupedReports.forEach { (time, groupOfReport) ->
            var dateTotalBalance = 0.0
            groupOfReport.forEach {
                dateTotalBalance += it.money
            }
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = time, style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(text = convertDoubleToMoney(dateTotalBalance), style = MaterialTheme.typography.h6)
                }

            }
            Divider()
            groupOfReport.forEach { report ->
                ReportRow(report = report, isWalletNameShow = isWalletNameShow) {
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
    isWalletNameShow: Boolean,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(convertStringToColor(report.color))
            )
            Column {
                Text(text = report.name, style = MaterialTheme.typography.body1)
                if(isWalletNameShow) {
                    Text(text = report.walletName, style = MaterialTheme.typography.overline)
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = convertDoubleToMoney(report.money), style = MaterialTheme.typography.body1)
        }
    }
}

@Preview
@Composable
fun HistoryListPreview() {
    HistoryList(reports = fakeReports, navToReportDetail = {}, isWalletNameShow = true)
}