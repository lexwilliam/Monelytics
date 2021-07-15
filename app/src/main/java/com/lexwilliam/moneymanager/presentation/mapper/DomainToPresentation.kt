package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Wallet
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation

internal fun Wallet.toPresentation(): WalletPresentation {
    return WalletPresentation(walletId, name, reports.map { it.toPresentation() })
}

internal fun Report.toPresentation(): ReportPresentation {
    return ReportPresentation(reportId, thisWalletId, timeAdded, name, money, reportType)
}