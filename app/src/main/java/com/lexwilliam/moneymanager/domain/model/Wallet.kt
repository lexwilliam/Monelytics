package com.lexwilliam.moneymanager.domain.model

data class Wallet(
    val id: Int,
    val name: String,
    val reports: List<Report>
)