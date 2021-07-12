package com.lexwilliam.moneymanager.domain.mapper

import com.lexwilliam.moneymanager.domain.model.Wallet
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation

internal fun WalletPresentation.toDomain(): Wallet {
    return Wallet(id, name, reports)
}