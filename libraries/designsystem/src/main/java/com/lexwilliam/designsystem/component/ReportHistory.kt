package com.lexwilliam.designsystem.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.ui.theme.MoneyManagerTheme
import com.lexwilliam.moneymanager.presentation.util.convertDoubleToMoney
import com.lexwilliam.moneymanager.presentation.util.fakeReports
import com.lexwilliam.moneymanager.presentation.util.formatDateToString

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
        val sortedReports = reports.sortedByDescending { it.timeAdded }
        val groupedReports = sortedReports.groupBy { formatDateToString(date = it.timeAdded!!, dateFormat = "EEE, dd MMM yyyy", todayYesterday = todayEnabled) }
        groupedReports.forEach { (time, groupOfReport) ->
            val sortByTime = groupOfReport.sortedByDescending { it.reportId }
            var dateTotalBalance = 0.0
            sortByTime.forEach {
                dateTotalBalance += it.money
            }
            Row {
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = time, style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(text = convertDoubleToMoney(dateTotalBalance), style = MaterialTheme.typography.h6)
                }

            }
            Divider()
            Log.d("History List", sortByTime.toString())
            sortByTime.forEach { report ->
                ReportRow(report = report, isWalletNameShow = isWalletNameShow) {
                    navToReportDetail(it)
                }
                Spacer(modifier = Modifier.padding(0.dp))
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReportIcon(iconId = report.iconId)
            Column {
                Text(text = report.name, style = MaterialTheme.typography.body1)
                if(isWalletNameShow) {
                    Text(text = report.walletName, style = MaterialTheme.typography.overline, color = Color.Gray)
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(text = convertDoubleToMoney(report.money), style = MaterialTheme.typography.body1)
        }
    }
}

@Preview
@Composable
fun HistoryListPreview() {
   MoneyManagerTheme {
       HistoryList(reports = fakeReports, navToReportDetail = {}, isWalletNameShow = true)
   }
}