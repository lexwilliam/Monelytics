package com.lexwilliam.domain.model

import java.time.LocalDate

data class Report(
    val reportId: Int,
    val walletName: String,
    val timeAdded: LocalDate?,
    val iconId: Int,
    val name: String,
    val money: Double,
    val reportType: ReportType
)