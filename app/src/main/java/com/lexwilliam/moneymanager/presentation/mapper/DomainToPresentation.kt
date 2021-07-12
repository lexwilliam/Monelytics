package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.moneymanager.domain.model.Wallet
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation

internal fun Wallet.toPresentation(): WalletPresentation {
    return WalletPresentation(id, name, reports)
}