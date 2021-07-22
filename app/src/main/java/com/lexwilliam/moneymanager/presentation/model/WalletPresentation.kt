package com.lexwilliam.moneymanager.presentation.model

data class WalletPresentation(
    val name: String,
    val reports: List<ReportPresentation>
)