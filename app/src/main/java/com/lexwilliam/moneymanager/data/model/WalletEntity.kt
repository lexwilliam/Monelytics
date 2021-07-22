package com.lexwilliam.moneymanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "wallet")
data class WalletEntity(
    @PrimaryKey val name: String
)