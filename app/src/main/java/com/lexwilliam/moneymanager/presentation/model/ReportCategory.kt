package com.lexwilliam.moneymanager.presentation.model

import androidx.compose.ui.graphics.Color
import com.lexwilliam.moneymanager.data.model.ReportType

data class ReportCategory(
    val name: String,
    val color: Color,
    val reportType: ReportType
)

//sealed class RepoCategory {
//    data class Income(val name: String, val color: Color)
//    data class Expense(val name: String, val color: Color)
//}