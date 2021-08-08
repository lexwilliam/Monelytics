package com.lexwilliam.moneymanager.data.mapper

import androidx.compose.ui.graphics.Color
import com.lexwilliam.moneymanager.data.model.ReportEntity
import com.lexwilliam.moneymanager.data.model.SubscriptionEntity
import com.lexwilliam.moneymanager.data.model.WalletWithReport
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Subscription
import com.lexwilliam.moneymanager.domain.model.Wallet

internal fun WalletWithReport.toDomain(): Wallet {
    return Wallet(
        name = wallet.name,
        iconId = wallet.iconId,
        reports = reports.map { it.toDomain() }
    )
}

internal fun ReportEntity.toDomain(): Report {
    return Report(reportId, walletName, timeAdded, iconId, name, money, reportType)
}

internal fun SubscriptionEntity.toDomain(): Subscription {
    return Subscription(name, walletName, iconId, cost, timePeriod, startDate, endDate, reportType)
}