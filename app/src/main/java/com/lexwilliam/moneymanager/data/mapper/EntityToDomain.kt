package com.lexwilliam.moneymanager.data.mapper

import com.lexwilliam.moneymanager.data.model.ReportEntity
import com.lexwilliam.moneymanager.domain.model.Report

internal fun ReportEntity.toDomain(): Report {
    return Report(id, name, money, reportType)
}