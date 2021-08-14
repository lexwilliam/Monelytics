package com.lexwilliam.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "wallet")
data class WalletEntity(
    @PrimaryKey val name: String,
    val iconId: Int,
    val timeAdded: Long = System.currentTimeMillis()
)