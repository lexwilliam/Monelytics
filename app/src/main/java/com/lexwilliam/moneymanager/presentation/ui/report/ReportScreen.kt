package com.lexwilliam.moneymanager.presentation.ui.report

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.util.fakeReport

@Composable
fun ReportScreen(
    viewModel: ReportViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    ReportContent(
        report = viewState.report,
        onBackPressed = { onBackPressed() }
    )
}

@Composable
fun ReportContent(
    report: ReportPresentation,
    onBackPressed: () -> Unit
) {
    Column {
        Text(text = report.name)
        Text(text = report.walletName)
        Text(text = report.money.toString())
        Text(text = report.timeAdded.toString())
        Text(text = report.reportType.toString())
        Text(text = report.reportId.toString())
    }
}

@Preview
@Composable
fun ReportPreview() {
    ReportContent(report = fakeReport, onBackPressed = {})
}