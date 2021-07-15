package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Wallet
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation

internal fun WalletPresentation.toDomain(): Wallet {
    return Wallet(walletId, name, reports.map { it.toDomain() })
}

internal fun ReportPresentation.toDomain(): Report {
    return Report(reportId, thisWalletId, timeAdded, name, money, reportType)
}