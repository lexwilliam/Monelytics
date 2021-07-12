package com.lexwilliam.moneymanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lexwilliam.moneymanager.data.dao.WalletDao
import com.lexwilliam.moneymanager.data.model.WalletEntity

@Database(entities = [WalletEntity::class], version = 1, exportSchema = false)
abstract class WalletDatabase: RoomDatabase() {
    abstract fun reportDao(): WalletDao
}