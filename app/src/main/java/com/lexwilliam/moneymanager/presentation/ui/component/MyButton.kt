package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.data.model.ReportType
import com.lexwilliam.moneymanager.presentation.model.ReportPresentation

@Composable
fun DoneButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .shadow(8.dp, MaterialTheme.shapes.medium, true)
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Done")
    }
}