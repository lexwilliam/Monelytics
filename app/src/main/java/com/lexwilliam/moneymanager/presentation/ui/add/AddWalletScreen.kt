package com.lexwilliam.moneymanager.presentation.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation

@Composable
fun AddWalletScreen(
    viewModel: AddWalletViewModel = viewModel(),
    navToHome: () -> Unit
) {
     AddWalletContent(
        insertWallet = viewModel::insertWallet,
         navToHome = { navToHome() }
    )
}

@Composable
fun AddWalletContent(
    insertWallet: (WalletPresentation) -> Unit,
    navToHome: () -> Unit
) {
    Column {
        Text(text = "Add Wallet")
        var nameText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = nameText,
            onValueChange = {
                nameText = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                insertWallet(WalletPresentation(name = nameText, reports = emptyList()))
                navToHome()
            })
        )
    }
}