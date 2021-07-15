package com.lexwilliam.moneymanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "wallet")
data class WalletEntity(
    @PrimaryKey(autoGenerate = true) val walletId: Int = 0,
    val name: String
)