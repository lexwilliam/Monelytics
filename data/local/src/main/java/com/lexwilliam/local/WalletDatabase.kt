package com.lexwilliam.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lexwilliam.local.dao.WalletDao
import com.lexwilliam.local.model.ReportEntity
import com.lexwilliam.local.model.WalletEntity

@Database(entities = [WalletEntity::class, ReportEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WalletDatabase: RoomDatabase() {
    abstract fun reportDao(): WalletDao
}