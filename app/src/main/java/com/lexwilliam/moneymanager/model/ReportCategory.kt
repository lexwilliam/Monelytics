package com.lexwilliam.moneymanager.model

import com.lexwilliam.local.model.ReportType

data class ReportCategory(
    val name: String,
    val iconId: Int,
    val reportType: ReportType
)

//sealed class RepoCategory {
//    data class Income(val name: String, val color: Color)
//    data class Expense(val name: String, val color: Color)
//}