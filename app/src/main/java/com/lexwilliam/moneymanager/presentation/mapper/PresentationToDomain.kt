package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation

internal fun ReportPresentation.toDomain(): Report {
    return Report(id, name, money, reportType)
}