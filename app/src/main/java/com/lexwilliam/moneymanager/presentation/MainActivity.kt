package com.lexwilliam.moneymanager.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.firebase.auth.FirebaseAuth
import com.lexwilliam.moneymanager.R
import com.lexwilliam.moneymanager.presentation.ui.login.LoginActivity
import com.lexwilliam.moneymanager.presentation.ui.theme.MoneyManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var mAuth: FirebaseAuth

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoneyManager)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

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