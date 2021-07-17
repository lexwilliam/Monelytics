package com.lexwilliam.moneymanager.presentation.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.DoneButton

@Composable
fun AddReportScreen(
    viewModel: AddReportViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    AddReportContent(
        walletId = viewModel.walletId.value,
        insertReport = viewModel::insertReport,
        onBackPressed = { onBackPressed() }
    )
}

@Composable
fun AddReportContent(
    walletId: Int,
    insertReport: (ReportPresentation) -> Unit,
    onBackPressed: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var nameText by remember{ mutableStateOf("") }
        OutlinedTextField(
            value = nameText,
            onValueChange = {
                nameText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            label = { Text("Name")}
        )

        var moneyText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = moneyText,
            onValueChange = {
                moneyText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
            label = { Text(text = "Amount") }
        )

        DoneButton(
            onClick = {
                insertReport(
                    ReportPresentation(
                        thisWalletId = walletId,
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