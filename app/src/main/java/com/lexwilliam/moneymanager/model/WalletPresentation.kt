package com.lexwilliam.moneymanager.model

data class WalletPresentation(
    val name: String,
    val iconId: Int,
    val reports: List<ReportPresentation>
)