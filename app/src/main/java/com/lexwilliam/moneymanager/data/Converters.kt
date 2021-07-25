package com.lexwilliam.moneymanager.data

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
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

    @TypeConverter
    fun fromColor(value: String?): Color? {
        var arr: List<String> = emptyList()
        var color: Color? = null
        value?.let {
            arr = value.split("/")
            color = Color(arr[0].toFloat(), arr[1].toFloat(), arr[2].toFloat())
        }
        return color
    }

    @TypeConverter
    fun stringToColor(color: Color?): String? {
        var result: String? = null
        if(color != null) {
            result = "${color.red}/${color.blue}/${color.green}"
            return result
        }
        return null
    }
}