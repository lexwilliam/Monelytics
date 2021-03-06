package com.lexwilliam.moneymanager.presentation.ui.report

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.R
import com.lexwilliam.local.model.ReportType
import com.lexwilliam.moneymanager.model.ReportCategory
import com.lexwilliam.moneymanager.model.ReportPresentation
import com.lexwilliam.designsystem.component.DoneButton
import com.lexwilliam.designsystem.component.ReportIcon
import java.time.LocalDate

@ExperimentalComposeUiApi
@Composable
fun AddReportScreen(
    viewModel: AddReportViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    var category by remember { mutableStateOf(ReportCategory("", R.drawable.report_file, reportType = ReportType.Default)) }
    var isEditCategoryShown by remember { mutableStateOf(false) }
    
    AddReportContent(
        walletName = viewState.walletName,
        isEditCategoryShown = isEditCategoryShown,
        setEditCategoryScreen = { isEditCategoryShown = it },
        category = category,
        setCategory = { category = it },
        insertReport = viewModel::insertReport,
        onBackPressed = { onBackPressed() }
    )
}

@ExperimentalComposeUiApi
@Composable
fun AddReportContent(
    walletName: String,
    isEditCategoryShown: Boolean,
    setEditCategoryScreen: (Boolean) -> Unit,
    category: ReportCategory,
    setCategory: (ReportCategory) -> Unit,
    insertReport: (ReportPresentation) -> Unit,
    onBackPressed: () -> Unit
) {
    
    if(isEditCategoryShown) {
        EditReportCategoryScreen(
            setCategory = {
                setCategory(it)
                setEditCategoryScreen(false)
            }
        )
    } else {
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(modifier = Modifier.padding(top = 64.dp), text = "Add Report", style = MaterialTheme.typography.h3)

            if(category.reportType == ReportType.Default) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { setEditCategoryScreen(true) },
                    value = "Add Report Category",
                    onValueChange = {},
                    enabled = false
                )
            } else {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    ReportIcon(iconId = category.iconId)
                    OutlinedTextField(modifier = Modifier
                        .fillMaxWidth(),
                        value = category.name,
                        onValueChange = {},
                        enabled = false
                    )
                }
            }
//        var nameText by remember{ mutableStateOf("") }
//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = nameText,
//            onValueChange = {
//                nameText = it
//            },
//            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//            keyboardActions = KeyboardActions(onDone = {
//                keyboardController?.hide()
//            }),
//            label = { Text("Report Name") },
//            singleLine = true
//        )
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
                    if(category.reportType == ReportType.Expense) {
                        moneyText = "-$moneyText"
                    }
                    insertReport(
                        ReportPresentation(
                            walletName = walletName,
                            timeAdded = LocalDate.now(),
                            iconId = category.iconId,
                            name = category.name,
                            money = moneyText.toDouble(),
                            reportType = category.reportType
                        )
                    )
                    onBackPressed()
                }
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun AddReportPreview() {
    AddReportContent(
        walletName = "Wallet 1",
        isEditCategoryShown = false,
        setEditCategoryScreen = {},
        category = ReportCategory("Salary", R.drawable.report_salary, ReportType.Income),
        setCategory =  {},
        insertReport = {},
        onBackPressed = {}
    )
}