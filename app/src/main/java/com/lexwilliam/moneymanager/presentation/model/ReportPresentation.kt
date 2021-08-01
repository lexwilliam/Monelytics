package com.lexwilliam.moneymanager.presentation.model

import com.lexwilliam.moneymanager.data.model.ReportType
import java.time.LocalDate
import java.util.*

data class ReportPresentation(
    val reportId: Int = 0,
    val walletName: String = "",
    val timeAdded: LocalDate?,
    val iconId: Int,
    val name: String,
    val money: Double,
    val reportType: ReportType
)