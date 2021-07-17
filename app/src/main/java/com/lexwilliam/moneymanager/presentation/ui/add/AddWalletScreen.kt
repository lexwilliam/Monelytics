package com.lexwilliam.moneymanager.presentation.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
         onBackPressed = { onBackPressed() }
    )
}

@ExperimentalComposeUiApi
@Composable
fun AddWalletContent(
    insertWallet: (WalletPresentation) -> Unit,
    onBackPressed: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(modifier = Modifier.padding(start = 32.dp, top = 64.dp), text = "Add Wallet", style = MaterialTheme.typography.h5)
        var nameText by remember { mutableStateOf("") }
        OutlinedTextField(
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

        DoneButton(
            onClick = {
                insertWallet(
                    WalletPresentation(
                        name = nameText,
                        reports = emptyList()
                    )
                )
                onBackPressed()
            }
        )
    }
}