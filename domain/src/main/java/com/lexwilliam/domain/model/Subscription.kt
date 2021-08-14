package com.lexwilliam.domain.model

import com.lexwilliam.local.model.ReportType
import java.time.LocalDate

data class Subscription(
    val name: String,
    val walletName: String,
    val iconId: Int,
    val cost: Double,
    val timePeriod: TimePeriod,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val reportType: ReportType
)