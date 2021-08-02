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

@Composable
fun MyBarChart() {
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
                entries.add(BarEntry(1.0f, 10.0f))
                entries.add(BarEntry(2.0f, 3.0f))
                entries.add(BarEntry(3.0f, 4.0f))
                entries.add(BarEntry(4.0f, 7.0f))
                val dataSet = BarDataSet(entries, "Month")
                data = BarData(dataSet)
                description = null
                isLogEnabled = true
                notifyDataSetChanged()
                invalidate()
            }
        }
    )
}