package com.lexwilliam.moneymanager.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class WalletWithReport(
    @Embedded val wallet: WalletEntity,
    @Relation(
        parentColumn = "walletId",
        entityColumn = "thisWalletId"
    )
    val reports: List<ReportEntity>
)