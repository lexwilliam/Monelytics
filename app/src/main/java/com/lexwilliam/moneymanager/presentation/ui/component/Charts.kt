package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.formatDateToString

@Composable
fun MyBarChart(
    reports: List<ReportPresentation>
) {
    val groupedReports = reports.groupBy { formatDateToString(it.timeAdded!!, "MMM") }
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 24.dp),
        factory = { context ->
            BarChart(context)
        },
        update = {
            it.apply {
                val entries = mutableListOf<BarEntry>()
                var currentIndex = 1f
                groupedReports.values.forEach { report ->
                    var totalBalanceInAMonth = 0f
                    reports.forEach {
                        totalBalanceInAMonth += it.money.toFloat()
                    }
                    entries.add(BarEntry(currentIndex, totalBalanceInAMonth))
                    currentIndex++
                }
                val dataSet = BarDataSet(entries, "")
                dataSet.stackLabels = groupedReports.keys.toTypedArray()
                data = BarData(dataSet)
                description = null
                isLogEnabled = true
                notifyDataSetChanged()
                invalidate()
            }
        }
    )
}