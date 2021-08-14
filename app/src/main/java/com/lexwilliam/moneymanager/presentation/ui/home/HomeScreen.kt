package com.lexwilliam.moneymanager

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.coil.rememberCoilPainter
import com.lexwilliam.moneymanager.model.ReportPresentation
import com.lexwilliam.moneymanager.model.WalletPresentation
import com.lexwilliam.designsystem.component.HistoryList
import com.lexwilliam.moneymanager.presentation.ui.home.HomeViewModel
import com.lexwilliam.moneymanager.presentation.ui.theme.MoneyManagerTheme
import com.lexwilliam.moneymanager.presentation.util.*


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    userName: String?,
    userPhoto: String?,
    navToAddWallet: () -> Unit,
    navToWalletDetail: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    Log.d("TAG", viewState.wallets.toString())
    HomeContent(
        wallets = viewState.wallets,
        reports = viewState.reports,
        isLoading = viewState.isLoading,
        userName = userName!!,
        userPhoto = userPhoto!!,
        navToAddWallet = { navToAddWallet() },
        navToWalletDetail = { navToWalletDetail(it) },
        navToReportDetail = { navToReportDetail(it) }
    )

}

@Composable
fun HomeContent(
    wallets: List<WalletPresentation>,
    reports: List<ReportPresentation>,
    isLoading: Boolean,
    userName: String,
    userPhoto: String,
    navToAddWallet: () -> Unit,
    navToWalletDetail: (String) -> Unit,
    navToReportDetail: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HomeTopAppBar(userName = userName, userPhoto = userPhoto)
        TotalBalance(wallets = wallets)
        WalletCardRowList(wallets = wallets, isLoading = isLoading, navToWalletDetail = { navToWalletDetail(it) }, navToAddWallet = { navToAddWallet()})
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = MaterialTheme.colors.primary)
            ) {
                Box(
                    modifier = Modifier
                        .height(24.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(Color.White)
                )
            }
            if(reports.isNotEmpty()) {
                HistoryList(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(start = 24.dp, end = 24.dp, bottom = 64.dp),
                    reports = reports,
                    navToReportDetail = { navToReportDetail(it) })
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Spacer(modifier = Modifier.padding(200.dp))
                }
            }
        }
    }
}

@Composable
fun HomeTopAppBar(
    userName: String,
    userPhoto: String
) {
    Row(
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colors.surface),
            contentScale = ContentScale.Crop,
            painter = rememberCoilPainter(
                request = userPhoto,
                fadeIn = true
            ),
            contentDescription = null
        )
        Text(text = "Hello $userName !", color = Color.White)
    }
}

@Composable
fun TotalBalance(
    modifier: Modifier = Modifier,
    wallets: List<WalletPresentation>
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "TOTAL BALANCE", style = MaterialTheme.typography.overline, color = MaterialTheme.colors.secondary)
        Text(text = convertDoubleToMoney(allWalletTotalBalance(wallets)), style = MaterialTheme.typography.h3, color = Color.White)
    }
}

@Composable
fun WalletCardRowList(
    wallets: List<WalletPresentation>,
    isLoading: Boolean,
    navToWalletDetail: (String) -> Unit,
    navToAddWallet: () -> Unit
) {
    if(wallets.isEmpty()) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(cardHeight)
                .fillMaxWidth()
                .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
                .background(color = Color.White)
                .clickable { navToAddWallet() }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Add Wallet", style = MaterialTheme.typography.h4)
            }
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(4.dp))
            wallets.forEach { wallet ->
                WalletCard(wallet = wallet, navToWalletDetail = { navToWalletDetail(it) })
            }
            AddWalletCard { navToAddWallet() }
        }
    }

}

@Composable
fun WalletCard(
    modifier: Modifier = Modifier,
    wallet: WalletPresentation,
    navToWalletDetail: (String) -> Unit
) {
    Column(
        modifier = modifier
            .width(cardWidth)
            .wrapContentHeight()
            .shadow(4.dp, MaterialTheme.shapes.medium, clip = true)
            .background(color = MaterialTheme.colors.secondary)
            .clickable { navToWalletDetail(wallet.name) }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = wallet.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.padding(36.dp))
            Text(text = "Balance", style = MaterialTheme.typography.caption)
            Text(text = convertDoubleToMoney(walletTotalBalance(wallet)), style = MaterialTheme.typography.h5)
        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.End
//        ) {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(Icons.Default.AccountBox, contentDescription = null)
//            }
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(Icons.Default.Edit, contentDescription = null)
//            }
//        }
    }
}

@Composable
fun AddWalletCard(
    navToAddWallet: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 100.dp)
            .padding(end = 24.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colors.primaryVariant)
            .clickable { navToAddWallet() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.Add,
            contentDescription = null,
            tint = MaterialTheme.colors.secondary
        )
    }
}

@Preview
@Composable
fun HomeContentPreview() {
    MoneyManagerTheme() {
        HomeContent(
            wallets = fakeWallets,
            reports = fakeReports,
            isLoading = false,
            userName = "POG",
            userPhoto = "",
            navToAddWallet = {},
            navToWalletDetail = {},
            navToReportDetail = {}
        )
    }
}