package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.domain.model.Wallet
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation

internal fun Wallet.toPresentation(): WalletPresentation {
    return WalletPresentation(name, reports.map { it.toPresentation() })
}

internal fun Report.toPresentation(): ReportPresentation {
    return ReportPresentation(reportId, walletName, timeAdded, name, money, reportType)
}