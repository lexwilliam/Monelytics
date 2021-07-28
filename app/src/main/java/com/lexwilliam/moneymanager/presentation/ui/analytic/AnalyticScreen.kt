package com.lexwilliam.moneymanager.presentation.ui.analytic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AnalyticScreen(
    viewModel: AnalyticViewModel = viewModel()
) {
    AnalyticContent()
    Box(
        modifier = Modifier.background(Color.White).fillMaxSize()
    )
}

@Composable
fun AnalyticContent(

) {

}