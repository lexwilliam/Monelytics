package com.lexwilliam.moneymanager.presentation.ui.wallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import com.lexwilliam.moneymanager.presentation.ui.component.DoneButton

@ExperimentalComposeUiApi
@Composable
fun AddWalletScreen(
    viewModel: AddWalletViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
     AddWalletContent(
         insertWallet = viewModel::insertWallet,
         insertReport = viewModel::insertReport,
         onBackPressed = { onBackPressed() }
    )
}

@ExperimentalComposeUiApi
@Composable
fun AddWalletContent(
    insertWallet: (WalletPresentation) -> Unit,
    insertReport: (ReportPresentation) -> Unit,
    onBackPressed: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(modifier = Modifier.padding(top = 64.dp), text = "Add Wallet", style = MaterialTheme.typography.h3)
        var nameText by remember { mutableStateOf("") }
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
            label = { Text("Wallet Name") },
            singleLine = true
        )
        var balanceText by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = balanceText,
            onValueChange = {
                balanceText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            label = { Text("Current Balance") },
            singleLine = true
        )
        DoneButton(
            onClick = {
                insertWallet(
                    WalletPresentation(
                        name = nameText,
                        reports = emptyList()
                    )
                )
                insertReport(
                    ReportPresentation(
                        walletName = nameText,
                        name = "First Deposit",
                        money = balanceText.toDouble(),
                        reportType = ReportType.Income
                    )
                )
                onBackPressed()
            }
        )
    }
}