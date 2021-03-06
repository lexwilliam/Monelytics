package com.lexwilliam.local.mapper

import com.lexwilliam.local.model.ReportEntity
import com.lexwilliam.local.model.SubscriptionEntity
import com.lexwilliam.local.model.WalletEntity
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Subscription
import com.lexwilliam.moneymanager.domain.model.Wallet

internal fun Wallet.toEntity(): WalletEntity {
    return WalletEntity(name, iconId)
}

internal fun Report.toEntity(): ReportEntity {
    return ReportEntity(
        reportId = reportId,
        walletName = walletName,
        timeAdded = timeAdded,
        iconId = iconId,
        name = name,
        money = money,
        reportType = reportType
    )
}

internal fun Subscription.toEntity(): SubscriptionEntity {
    return SubscriptionEntity(name, walletName, iconId, cost, timePeriod, startDate, endDate, reportType)
}