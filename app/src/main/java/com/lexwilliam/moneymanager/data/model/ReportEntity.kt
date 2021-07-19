package com.lexwilliam.moneymanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet_report")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true) val reportId: Int = 0,
    val thisWalletId: Int,
    val timeAdded: Long,
    val name: String,
    val money: Double,
    val reportType: ReportType
)

enum class ReportType {
    Expense, Income, Default
}