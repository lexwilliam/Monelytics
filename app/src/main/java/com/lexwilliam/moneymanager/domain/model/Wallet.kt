package com.lexwilliam.moneymanager.domain.model

data class Wallet(
    val name: String,
    val reports: List<Report>
)