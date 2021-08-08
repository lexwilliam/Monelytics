package com.lexwilliam.moneymanager.domain.model

import com.lexwilliam.moneymanager.data.model.ReportType
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