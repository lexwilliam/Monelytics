package com.lexwilliam.moneymanager.presentation.ui.analytic

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.lexwilliam.moneymanager.presentation.ui.component.MyBarChart

@Composable
fun AnalyticScreen(
    viewModel: AnalyticViewModel = viewModel()
) {
    MyBarChart()
    AnalyticContent()
}

@Composable
fun AnalyticContent(

) {

}