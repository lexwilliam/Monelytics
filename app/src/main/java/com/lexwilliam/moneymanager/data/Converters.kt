package com.lexwilliam.moneymanager.data

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import com.lexwilliam.moneymanager.data.model.ReportType
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.*

class Converters {
    @TypeConverter
    fun fromReportType(value: String?): ReportType? {
        return value?.let { enumValueOf<ReportType>(it) }
    }

    @TypeConverter
    fun watchStatusToString(status: ReportType?): String? {
        return status?.name
    }

    @TypeConverter
    fun fromLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(value) }
    }

    @TypeConverter
    fun longToLocalDate(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}