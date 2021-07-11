package com.lexwilliam.moneymanager.data.mapper

import com.lexwilliam.moneymanager.data.model.ReportEntity
import com.lexwilliam.moneymanager.domain.model.Report

internal fun Report.toEntity(): ReportEntity {
    return ReportEntity(
        name = name,
        money = money,
        reportType = reportType
    )
}