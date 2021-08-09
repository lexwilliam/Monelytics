package com.lexwilliam.moneymanager.data.mapper

import com.lexwilliam.moneymanager.data.Converters
import com.lexwilliam.moneymanager.data.model.ReportEntity
import com.lexwilliam.moneymanager.data.model.ReportResponse

fun ReportEntity.toResponse(): ReportResponse {
    return ReportResponse(
        walletName = walletName,
        timeAdded = Converters().localDateToLong(timeAdded)!!,
        iconId = iconId,
        name = name,
        money = money,
        reportType = Converters().watchStatusToString(reportType)!!
    )
}