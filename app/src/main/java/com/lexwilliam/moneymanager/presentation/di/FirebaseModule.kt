package com.lexwilliam.moneymanager.presentation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthSettings
import com.google.firebase.firestore.BuildConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        val instance = FirebaseFirestore.getInstance()
        if(BuildConfig.DEBUG) {
            instance.useEmulator("10.0.2.2", 8080)
        }
        return instance
    }


    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        val instance = FirebaseAuth.getInstance()
        if(BuildConfig.DEBUG) {
            instance.useEmulator("10.0.2.2", 9099)
        }
        return instance
    }
}