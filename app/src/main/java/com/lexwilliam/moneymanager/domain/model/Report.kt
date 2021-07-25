package com.lexwilliam.moneymanager.domain.model

import androidx.compose.ui.graphics.Color
import com.lexwilliam.moneymanager.data.model.ReportType

data class Report(
    val reportId: Int,
    val walletName: String,
    val timeAdded: Long,
    val color: String,
    val name: String,
    val money: Double,
    val reportType: ReportType
)