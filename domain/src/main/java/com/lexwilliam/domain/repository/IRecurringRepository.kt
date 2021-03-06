package com.lexwilliam.domain.repository

import com.lexwilliam.domain.model.Subscription
import kotlinx.coroutines.flow.Flow

interface IRecurringRepository {

    suspend fun getAllSubscriptions(): Flow<List<Subscription>>

    suspend fun getSubscriptionByName(name: String): Flow<Subscription>

    suspend fun insertSubscription(): Flow<Long>

    suspend fun deleteSubscription(): Flow<Int>

    suspend fun updateSubscription(): Flow<Int>

}