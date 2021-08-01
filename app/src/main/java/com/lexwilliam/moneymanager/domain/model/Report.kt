package com.lexwilliam.moneymanager.domain.model

import androidx.compose.ui.graphics.Color
import com.lexwilliam.moneymanager.data.model.ReportType
import java.time.LocalDate
import java.util.*

data class Report(
    val reportId: Int,
    val walletName: String,
    val timeAdded: LocalDate?,
    val iconId: Int,
    val name: String,
    val money: Double,
    val reportType: ReportType
)