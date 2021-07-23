package com.lexwilliam.moneymanager.presentation.model

data class WalletPresentation(
    val name: String,
    val iconId: Int,
    val reports: List<ReportPresentation>
)