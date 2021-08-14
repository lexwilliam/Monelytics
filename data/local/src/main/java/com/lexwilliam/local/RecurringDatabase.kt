package com.lexwilliam.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lexwilliam.local.dao.SubscriptionDao
import com.lexwilliam.local.model.SubscriptionEntity

@Database(entities = [SubscriptionEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecurringDatabase: RoomDatabase() {
    abstract fun subscriptionDao(): SubscriptionDao
}