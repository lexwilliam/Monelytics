package com.lexwilliam.moneymanager.presentation.ui.component

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.lexwilliam.moneymanager.data.model.ReportType
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
                val incomeEntries = mutableListOf<BarEntry>()
                val expenseEntries = mutableListOf<BarEntry>()
                var currentIndex = 1f
                groupedReports.values.forEach { report ->
                    var income = 0f
                    var expense = 0f
                    report.forEach {
                        when(it.reportType) {
                            ReportType.Income -> income += it.money.toFloat()
                            ReportType.Expense -> expense -= it.money.toFloat()
                            else -> Log.d("BarChart", "ReportType is Default")
                        }
                    }
                    incomeEntries.add(BarEntry(currentIndex, income))
                    expenseEntries.add(BarEntry(currentIndex, expense))
                    currentIndex++
                }
                val incomeDataSet = BarDataSet(incomeEntries, "Income")
                incomeDataSet.color = android.graphics.Color.argb(255, 25, 25, 23)
                val expenseDataSet = BarDataSet(expenseEntries, "Expense")
                expenseDataSet.color = android.graphics.Color.argb(255, 254, 238, 88)

                xAxis.valueFormatter = IndexAxisValueFormatter(groupedReports.keys)
                axisLeft.isEnabled = false

                data = BarData(incomeDataSet, expenseDataSet)
                data.barWidth = 0.15f

                description = null
                isLogEnabled = true
                notifyDataSetChanged()
                invalidate()
            }
        }
    )
}