package com.lexwilliam.moneymanager.presentation.util

import android.annotation.SuppressLint
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.WalletPresentation
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.days

val cardWidth = 360.dp
val cardHeight = 180.dp

val thisMonth = convertLongToTime(System.currentTimeMillis(), "MMM yyyy")

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(time: Long, dateFormat: String): String {
    val date = Date(time)
    val format = SimpleDateFormat(dateFormat)
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

fun getWalletIncome(wallet: WalletPresentation): Double {
    var totalMoney = 0.0
    wallet.reports.forEach { report ->
        if(report.reportType == ReportType.Income) {
            totalMoney += report.money
        }
    }
    return totalMoney
}

fun getWalletExpense(wallet: WalletPresentation): Double {
    var totalMoney = 0.0
    wallet.reports.forEach { report ->
        if(report.reportType == ReportType.Expense) {
            totalMoney += report.money
        }
    }
    return totalMoney
}

fun convertIntToMoneyFormat(num: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    return numberFormat.format(num)
}