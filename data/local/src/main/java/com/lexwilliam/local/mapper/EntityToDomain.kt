package com.lexwilliam.local.mapper

import com.lexwilliam.local.model.ReportEntity
import com.lexwilliam.local.model.SubscriptionEntity
import com.lexwilliam.local.model.WalletWithReport
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