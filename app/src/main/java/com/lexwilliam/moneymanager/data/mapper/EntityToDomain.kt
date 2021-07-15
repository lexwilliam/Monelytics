package com.lexwilliam.moneymanager.data.mapper

import com.lexwilliam.moneymanager.data.model.ReportEntity
import com.lexwilliam.moneymanager.data.model.WalletWithReport
import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Wallet

internal fun WalletWithReport.toDomain(): Wallet {
    return Wallet(
        walletId = wallet.walletId,
        name = wallet.name,
        reports = reports.map { it.toDomain() }
    )
}

internal fun ReportEntity.toDomain(): Report {
    return Report(reportId, thisWalletId, timeAdded, name, money, reportType)
}