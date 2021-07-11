package com.lexwilliam.moneymanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_table")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val money: Double,
    val reportType: ReportType,
    val timeAdded: Long = System.currentTimeMillis()
)

enum class ReportType{
    Expense, Income
}