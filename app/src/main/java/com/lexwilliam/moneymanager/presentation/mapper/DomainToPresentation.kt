package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.domain.model.Report
import com.lexwilliam.domain.model.Wallet
import com.lexwilliam.moneymanager.model.ReportPresentation
import com.lexwilliam.moneymanager.model.WalletPresentation

internal fun Wallet.toPresentation(): WalletPresentation {
    return WalletPresentation(name, iconId, reports.map { it.toPresentation() })
}

internal fun Report.toPresentation(): ReportPresentation {
    return ReportPresentation(reportId, walletName, timeAdded, iconId, name, money, reportType)
}