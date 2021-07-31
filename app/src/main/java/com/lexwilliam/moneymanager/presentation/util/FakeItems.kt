package com.lexwilliam.moneymanager.presentation.util

import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation

val fakeReport = ReportPresentation(
    reportId = 0,
    walletName = "Wallet 1",
    iconId = R.drawable.report_file,
    name = "Report 1",
    money = 100.0,
    reportType = ReportType.Income
)

val fakeReports = listOf(
    ReportPresentation(
        reportId = 0,
        walletName = "Wallet 1",
        iconId = R.drawable.report_file,
        name = "Report 1",
        money = 100.0,
        reportType = ReportType.Income
    ),
    ReportPresentation(
        reportId = 1,
        walletName = "Wallet 1",
        iconId = R.drawable.report_file,
        name = "Report 2",
        money = 50.0,
        reportType = ReportType.Expense
    ),
    ReportPresentation(
        reportId = 2,
        walletName = "Wallet 2",
        iconId = R.drawable.report_file,
        name = "Report 3",
        money = 50.0,
        reportType = ReportType.Expense
    ),
    ReportPresentation(
        reportId = 3,
        walletName = "Wallet 2",
        iconId = R.drawable.report_file,
        name = "Report 4",
        money = 25.0,
        reportType = ReportType.Income
    )
)

val fakeWallet = WalletPresentation(
    "Wallet 1",
    R.drawable.account_balance_wallet_black_24dp,
    fakeReports
)

val fakeWallets = listOf(
    WalletPresentation(
        "Wallet 1",
        R.drawable.account_balance_wallet_black_24dp,
        fakeReports
    ),
    WalletPresentation(
        "Wallet 2",
        R.drawable.shopping_bag_black_24dp,
        fakeReports
    )
)