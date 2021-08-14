package com.lexwilliam.moneymanager.presentation.ui.wallet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.model.WalletPresentation
import com.lexwilliam.designsystem.component.DoneButton
import com.lexwilliam.moneymanager.presentation.util.walletIconResources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun AddWalletScreen(
    viewModel: AddWalletViewModel = viewModel(),
    onBackPressed: () -> Unit
) {
    var currentIcon by remember { mutableStateOf(R.drawable.account_balance_wallet_black_24dp)}

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent =  {
            WalletIconEditor(
                currentIcon = currentIcon,
                iconResource = walletIconResources,
                onIconSelected = {
                    currentIcon = it
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            )
        }
    ) {
        AddWalletContent(
            currentIcon = currentIcon,
            insertWallet = viewModel::insertWallet,
            onBackPressed = { onBackPressed() },
            bottomSheetState = bottomSheetScaffoldState,
            coroutineScope = coroutineScope
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun AddWalletContent(
    currentIcon: Int,
    insertWallet: (WalletPresentation) -> Unit,
    onBackPressed: () -> Unit,
    bottomSheetState: BottomSheetScaffoldState,
    coroutineScope: CoroutineScope
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 64.dp),
            text = "Add Wallet",
            style = MaterialTheme.typography.h3
        )
        var nameText by remember { mutableStateOf("") }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier
                    .height(IntrinsicSize.Max),
                onClick = { coroutineScope.launch { bottomSheetState.bottomSheetState.expand() } }
            ) {
                Icon(painter = painterResource(id = currentIcon), contentDescription = null)
            }
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
        }
        DoneButton(
            onClick = {
                insertWallet(
                    WalletPresentation(
                        name = nameText,
                        iconId = currentIcon,
                        reports = emptyList()
                    )
                )
                onBackPressed()
            }
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun WalletIconEditor(
    currentIcon: Int,
    iconResource: List<Int>,
    onIconSelected: (Int) -> Unit
) {
    var selectedIcon by remember { mutableStateOf(currentIcon) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(text = "Select Wallet Icon", style = MaterialTheme.typography.h6)
        LazyVerticalGrid(
            cells = GridCells.Adaptive(48.dp)
        ) {
            items(items = iconResource) { item ->
                IconButton(onClick = {
                    selectedIcon = item
                    onIconSelected(item)
                }) {
                    Icon(painter = painterResource(id = item), contentDescription = null)
                }
            }
        }
        Spacer(modifier = Modifier.padding(28.dp))
    }
}