package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.domain.model.Report
import com.lexwilliam.domain.model.Wallet
import com.lexwilliam.moneymanager.model.ReportPresentation
import com.lexwilliam.moneymanager.model.WalletPresentation

internal fun WalletPresentation.toDomain(): Wallet {
    return Wallet(name, iconId, reports.map { it.toDomain() })
}

internal fun ReportPresentation.toDomain(): Report {
    return Report(reportId, walletName, timeAdded, iconId, name, money, reportType)
}