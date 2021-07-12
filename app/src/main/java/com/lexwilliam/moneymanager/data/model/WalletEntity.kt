package com.lexwilliam.moneymanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lexwilliam.moneymanager.domain.model.Report

@Entity(tableName = "wallet")
data class WalletEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val moneyTotal: Double,
    val reports: List<Report>
)