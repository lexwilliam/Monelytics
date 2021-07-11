package com.lexwilliam.moneymanager.domain.model

import androidx.room.PrimaryKey
import com.lexwilliam.moneymanager.data.model.ReportType

data class Report(
    val id: Int,
    val name: String,
    val money: Double,
    val reportType: ReportType
)