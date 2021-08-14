package com.lexwilliam.local.mapper

import com.lexwilliam.local.Converters
import com.lexwilliam.local.model.ReportEntity
import com.lexwilliam.local.model.ReportResponse

internal fun ReportResponse.toEntity(): ReportEntity {
    return ReportEntity(reportId, walletName, Converters().fromLocalDate(timeAdded), iconId, name, money, Converters().fromReportType(reportType)!!)
}