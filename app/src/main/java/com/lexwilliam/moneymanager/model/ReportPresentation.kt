package com.lexwilliam.moneymanager.model

import com.lexwilliam.local.model.ReportType
import java.time.LocalDate

data class ReportPresentation(
    val reportId: Int = 0,
    val walletName: String = "",
    val timeAdded: LocalDate?,
    val iconId: Int,
    val name: String,
    val money: Double,
    val reportType: ReportType
)