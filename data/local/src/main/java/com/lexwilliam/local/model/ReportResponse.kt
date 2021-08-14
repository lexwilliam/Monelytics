package com.lexwilliam.local.model

import java.time.LocalDate

data class ReportResponse(
    val reportId: Int,
    val walletName: String,
    val timeAdded: Long,
    val iconId: Int,
    val name: String,
    val money: Double,
    val reportType: String
)