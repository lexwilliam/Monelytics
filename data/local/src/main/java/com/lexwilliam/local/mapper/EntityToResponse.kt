package com.lexwilliam.local.mapper

import com.lexwilliam.local.Converters
import com.lexwilliam.local.model.ReportEntity
import com.lexwilliam.local.model.ReportResponse

fun ReportEntity.toResponse(): ReportResponse {
    return ReportResponse(
        reportId = reportId,
        walletName = walletName,
        timeAdded = Converters().localDateToLong(timeAdded)!!,
        iconId = iconId,
        name = name,
        money = money,
        reportType = Converters().watchStatusToString(reportType)!!
    )
}