package com.lexwilliam.moneymanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lexwilliam.moneymanager.domain.model.TimePeriod
import java.time.LocalDate

@Entity(tableName = "subscription")
data class SubscriptionEntity(
    @PrimaryKey
    val name: String,
    val walletName: String,
    val iconId: Int,
    val cost: Double,
    val timePeriod: TimePeriod,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val reportType: ReportType
)