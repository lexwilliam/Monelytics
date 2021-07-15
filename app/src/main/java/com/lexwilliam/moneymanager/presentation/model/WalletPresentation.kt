package com.lexwilliam.moneymanager.presentation.model

data class WalletPresentation(
    val walletId: Int = 0,
    val name: String,
    val reports: List<ReportPresentation>
)