package com.lexwilliam.moneymanager.data.dao

import androidx.room.*
import com.lexwilliam.moneymanager.data.model.SubscriptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionDao {

    @Query("SELECT * FROM subscription")
    fun getAllSubscriptions(): Flow<List<SubscriptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscription(subscription: SubscriptionEntity): Long

    @Delete
    suspend fun deleteSubscription(subscription: SubscriptionEntity): Int

    @Update
    suspend fun updateSubscription(subscription: SubscriptionEntity): Int
}