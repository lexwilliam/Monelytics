package com.lexwilliam.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class WalletWithReport(
    @Embedded val wallet: WalletEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "walletName"
    )
    val reports: List<ReportEntity>
)