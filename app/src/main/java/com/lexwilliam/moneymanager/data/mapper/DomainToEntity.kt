package com.lexwilliam.moneymanager.data.mapper

import com.lexwilliam.moneymanager.data.model.WalletEntity
import com.lexwilliam.moneymanager.domain.model.Wallet

internal fun Wallet.toEntity(): WalletEntity {
    return WalletEntity(id, name, reports)
}