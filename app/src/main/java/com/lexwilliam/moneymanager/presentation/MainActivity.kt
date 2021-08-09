package com.lexwilliam.moneymanager.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.presentation.ui.login.LoginActivity
import com.lexwilliam.moneymanager.presentation.ui.theme.MoneyManagerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.google.firebase.firestore.CollectionReference
import com.lexwilliam.moneymanager.data.model.WalletEntity
import com.lexwilliam.moneymanager.domain.model.Wallet
import com.lexwilliam.moneymanager.domain.usecase.GetAllWalletsFromFirestore


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var firebaseAuth: FirebaseAuth
    @Inject lateinit var firestore: FirebaseFirestore

    @ExperimentalPagerApi
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoneyManager)

        val currentUser = firebaseAuth.currentUser

        if(currentUser == null) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

        setContent {
            MoneyManagerTheme {
                MoneyManagerApp(
                    email = currentUser?.email,
                    userName = currentUser?.displayName,
                    userPhoto = currentUser?.photoUrl.toString()
                )
            }
        }
    }
}