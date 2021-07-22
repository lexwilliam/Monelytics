package com.lexwilliam.moneymanager.presentation.model

import com.lexwilliam.moneymanager.data.model.ReportType

data class ReportPresentation(
    val reportId: Int = 0,
    val walletName: String = "",
    val timeAdded: Long = System.currentTimeMillis(),
    val name: String,
    val money: Double,
    val reportType: ReportType
)