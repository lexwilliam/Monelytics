package com.lexwilliam.moneymanager.presentation.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthSettings
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

    // To toggle between local emulator or production mode
    var isFirebaseLocal = true

    @Singleton
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        val instance = FirebaseFirestore.getInstance()

        val settings = if (isFirebaseLocal) {
            FirebaseFirestoreSettings.Builder()
                .setHost("10.0.2.2:8080")
                .setSslEnabled(false)
                .setPersistenceEnabled(false)
                .build()
        } else {
            FirebaseFirestoreSettings.Builder()
                .setSslEnabled(false)
                .setPersistenceEnabled(false)
                .build()
        }
        instance.firestoreSettings = settings
        return instance
    }


    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        val instance = FirebaseAuth.getInstance()
        return instance
    }
}