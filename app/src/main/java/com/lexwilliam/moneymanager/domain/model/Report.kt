package com.lexwilliam.moneymanager.domain.model

data class Report(
    val timeAdded: Long = System.currentTimeMillis(),
    val name: String,
    val money: Double,
    val reportType: ReportType
)