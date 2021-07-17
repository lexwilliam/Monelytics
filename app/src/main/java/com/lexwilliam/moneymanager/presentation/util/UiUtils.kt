package com.lexwilliam.moneymanager.presentation.util

import android.annotation.SuppressLint
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import java.text.SimpleDateFormat
import java.util.*

val cardWidth = 200.dp
val cardHeight = 240.dp

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy/MM/dd")
    return format.format(date)
}

fun walletTotalBalance(wallet: WalletPresentation): Double {
    var totalMoney = 0.0
    wallet.reports.forEach {
        totalMoney += it.money
    }
    return totalMoney
}

fun allWalletTotalBalance(wallets: List<WalletPresentation>): Double {
    var totalBalance = 0.0
    wallets.forEach { wallet ->
        wallet.reports.forEach { report ->
            totalBalance += report.money
        }
    }
    return totalBalance
}