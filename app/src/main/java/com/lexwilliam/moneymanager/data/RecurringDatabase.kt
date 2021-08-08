package com.lexwilliam.moneymanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lexwilliam.moneymanager.data.dao.SubscriptionDao
import com.lexwilliam.moneymanager.data.dao.WalletDao
import com.lexwilliam.moneymanager.data.model.ReportEntity
import com.lexwilliam.moneymanager.data.model.SubscriptionEntity
import com.lexwilliam.moneymanager.data.model.WalletEntity

@Database(entities = [SubscriptionEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecurringDatabase: RoomDatabase() {
    abstract fun subscriptionDao(): SubscriptionDao
}