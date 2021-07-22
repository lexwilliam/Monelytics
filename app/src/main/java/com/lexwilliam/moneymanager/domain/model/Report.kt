package com.lexwilliam.moneymanager.domain.model

import com.lexwilliam.moneymanager.data.model.ReportType

data class Report(
    val reportId: Int,
    val walletName: String,
    val timeAdded: Long,
    val name: String,
    val money: Double,
    val reportType: ReportType
)