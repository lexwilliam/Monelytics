package com.lexwilliam.moneymanager.presentation.ui.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.DoneButton

@Composable
fun AddReportScreen(
    viewModel: AddReportViewModel = viewModel(),
    navToHome: () -> Unit
) {
    AddReportContent(
        insertReport = viewModel::insertReport,
        navToHome = { navToHome() }
    )
}

@Composable
fun AddReportContent(
    insertReport: (ReportPresentation) -> Unit,
    navToHome: () -> Unit
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
        )

        var moneyText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = moneyText,
            onValueChange = {
                moneyText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
        )

        DoneButton(
            onClick = {
                insertReport(
                    ReportPresentation(
                        name = nameText,
                        money = moneyText.toDouble(),
                        reportType = ReportType.Expense
                    )
                )
                navToHome()
            }
        )
    }
}