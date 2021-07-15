package com.lexwilliam.moneymanager.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.lexwilliam.moneymanager.data.model.ReportType

class Converters {
    @TypeConverter
    fun fromReportType(value: String?): ReportType? {
        return value?.let { enumValueOf<ReportType>(it) }
    }

    @TypeConverter
    fun watchStatusToString(status: ReportType?): String? {
        return status?.name
    }
}