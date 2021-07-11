package com.lexwilliam.moneymanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lexwilliam.moneymanager.data.dao.ReportDao
import com.lexwilliam.moneymanager.data.model.ReportEntity

@Database(entities = [ReportEntity::class], version = 1, exportSchema = false)
abstract class ReportDatabase: RoomDatabase() {
    abstract fun reportDao(): ReportDao
}