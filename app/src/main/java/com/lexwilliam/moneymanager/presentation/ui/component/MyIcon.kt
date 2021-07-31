package com.lexwilliam.moneymanager.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lexwilliam.moneymanager.R

@Composable
fun ReportIcon(
    modifier: Modifier = Modifier,
    iconId: Int
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(MaterialTheme.shapes.small)
    ) {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.secondary).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = iconId),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun ReportIconPreview() {
    ReportIcon(iconId = R.drawable.report_salary)
}