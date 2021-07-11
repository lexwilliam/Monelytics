package com.lexwilliam.moneymanager.presentation.model

import com.lexwilliam.moneymanager.data.model.ReportType

data class ReportPresentation(
    val id: Int = 0,
    val name: String,
    val money: Double,
    val reportType: ReportType
)