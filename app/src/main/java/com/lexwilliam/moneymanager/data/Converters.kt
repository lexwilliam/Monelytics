package com.lexwilliam.moneymanager.data

import androidx.room.TypeConverter
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.domain.model.TimePeriod
import java.time.LocalDate

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
    fun fromTimePeriod(value: String?): TimePeriod? {
        return value?.let { enumValueOf<TimePeriod>(it) }
    }

    @TypeConverter
    fun timePeriodToString(period: TimePeriod?): String? {
        return period?.name
    }

    @TypeConverter
    fun fromLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(value) }
    }

    @TypeConverter
    fun localDateToLong(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}