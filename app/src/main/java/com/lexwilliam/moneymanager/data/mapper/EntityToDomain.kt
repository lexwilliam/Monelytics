package com.lexwilliam.moneymanager.data.mapper

import com.lexwilliam.moneymanager.data.model.WalletEntity
import com.lexwilliam.moneymanager.domain.model.Wallet

internal fun WalletEntity.toDomain(): Wallet {
    return Wallet(id, name, reports)
}