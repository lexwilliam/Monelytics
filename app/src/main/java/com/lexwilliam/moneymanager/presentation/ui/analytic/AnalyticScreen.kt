package com.lexwilliam.moneymanager.presentation.ui.analytic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.presentation.ui.profile.AnalyticContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikephil.charting.charts.BarChart
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.MyBarChart

@Composable
fun AnalyticScreen(
    viewModel: AnalyticViewModel = viewModel()
) {
    val viewState by viewModel.state.collectAsState()

    AnalyticContent(
        reports = viewState.reports
    )
}

@Composable
fun AnalyticContent(
    reports: List<ReportPresentation>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(modifier = Modifier.padding(top = 24.dp), text = "Analytics")

        MyBarChart(reports = reports)
    }
}

@Composable
fun RoundedBoxContainer(
    content: @Composable () -> Unit,
    color: Color = Color.White
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .shadow(4.dp, MaterialTheme.shapes.medium, true)
    ) {

    }
}