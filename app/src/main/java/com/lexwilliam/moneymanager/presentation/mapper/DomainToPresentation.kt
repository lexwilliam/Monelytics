package com.lexwilliam.moneymanager.presentation.mapper

import com.lexwilliam.moneymanager.domain.model.Report
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation

internal fun Report.toPresentation(): ReportPresentation {
    return ReportPresentation(id, name, money, reportType)
}