package com.lexwilliam.moneymanager.presentation.model

import com.lexwilliam.moneymanager.domain.model.Report

data class WalletPresentation(
    val id: Int = 0,
    val name: String,
    val reports: List<Report>
)