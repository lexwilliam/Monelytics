package com.lexwilliam.moneymanager.presentation.ui.report

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.DoneButton

@ExperimentalComposeUiApi
@Composable
fun AddReportScreen(
    viewModel: AddReportViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    AddReportContent(
        walletName = viewModel.walletName.value,
        insertReport = viewModel::insertReport,
        onBackPressed = { onBackPressed() }
    )
}

@ExperimentalComposeUiApi
@Composable
fun AddReportContent(
    walletName: String,
    insertReport: (ReportPresentation) -> Unit,
    onBackPressed: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(modifier = Modifier.padding(top = 64.dp), text = "Add Report", style = MaterialTheme.typography.h3)
        var nameText by remember{ mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nameText,
            onValueChange = {
                nameText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            label = { Text("Report Name") },
            singleLine = true
        )
        var moneyText by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = moneyText,
            onValueChange = {
                moneyText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            label = { Text("Amount") },
            singleLine = true
        )
        DoneButton(
            onClick = {
                insertReport(
                    ReportPresentation(
                        walletName = walletName,
                        timeAdded = System.currentTimeMillis(),
                        name = nameText,
                        money = moneyText.toDouble(),
                        reportType = ReportType.Income
                    )
                )
                onBackPressed()
            }
        )
    }
}