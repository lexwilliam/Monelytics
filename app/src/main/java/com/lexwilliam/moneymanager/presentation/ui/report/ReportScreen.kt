package com.lexwilliam.moneymanager.presentation.ui.report

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation

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
        Text(text = report.thisWalletId.toString())
        Text(text = report.money.toString())
        Text(text = report.timeAdded.toString())
        Text(text = report.reportType.toString())
        Text(text = report.reportId.toString())
    }
}