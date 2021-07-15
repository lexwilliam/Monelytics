package com.lexwilliam.moneymanager.domain.model

data class Wallet(
    val walletId: Int,
    val name: String,
    val reports: List<Report>
)