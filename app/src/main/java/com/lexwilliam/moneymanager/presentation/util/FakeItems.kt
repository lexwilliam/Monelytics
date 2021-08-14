package com.lexwilliam.moneymanager.presentation.util

import com.lexwilliam.moneymanager.R
import com.lexwilliam.local.model.ReportType
import com.lexwilliam.moneymanager.model.ReportPresentation
import com.lexwilliam.moneymanager.model.WalletPresentation
import java.time.LocalDate

val fakeReport = ReportPresentation(
    reportId = 0,
    walletName = "Wallet 1",
    timeAdded = LocalDate.now(),
    iconId = R.drawable.report_file,
    name = "Report 1",
    money = 100.0,
    reportType = ReportType.Income
)

val fakeReports = listOf(
    ReportPresentation(
        reportId = 0,
        walletName = "Wallet 1",
        timeAdded = LocalDate.now(),
        iconId = R.drawable.report_file,
        name = "Report 1",
        money = 20.0,
        reportType = ReportType.Income
    ),
    ReportPresentation(
        reportId = 1,
        walletName = "Wallet 1",
        timeAdded = LocalDate.of(2021, 7, 10),
        iconId = R.drawable.report_file,
        name = "Report 2",
        money = -50.0,
        reportType = ReportType.Expense
    ),
    ReportPresentation(
        reportId = 2,
        walletName = "Wallet 1",
        timeAdded = LocalDate.of(2021, 7, 10),
        iconId = R.drawable.report_file,
        name = "Report 3",
        money = 50.0,
        reportType = ReportType.Income
    ),
    ReportPresentation(
        reportId = 3,
        walletName = "Wallet 1",
        timeAdded = LocalDate.of(2021, 6, 10),
        iconId = R.drawable.report_file,
        name = "Report 4",
        money = -25.0,
        reportType = ReportType.Expense
    ),
    ReportPresentation(
        reportId = 0,
        walletName = "Wallet 1",
        timeAdded = LocalDate.of(2021, 5, 10),
        iconId = R.drawable.report_file,
        name = "Report 1",
        money = -75.0,
        reportType = ReportType.Expense
    ),
    ReportPresentation(
        reportId = 0,
        walletName = "Wallet 1",
        timeAdded = LocalDate.of(2021, 5, 10),
        iconId = R.drawable.report_file,
        name = "Report 1",
        money = 50.0,
        reportType = ReportType.Income
    ),
    ReportPresentation(
        reportId = 0,
        walletName = "Wallet 1",
        timeAdded = LocalDate.of(2021, 4, 10),
        iconId = R.drawable.report_file,
        name = "Report 1",
        money = 25.0,
        reportType = ReportType.Income
    ),
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