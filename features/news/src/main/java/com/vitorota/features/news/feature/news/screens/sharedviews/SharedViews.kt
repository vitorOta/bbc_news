package com.vitorota.features.news.feature.news.screens.sharedviews

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NewsErrorText(errorMessage: String, modifier: Modifier = Modifier) {
    Text(
        text = "Error: $errorMessage", color = Color.Red, textAlign = TextAlign.Center,
        modifier = modifier
            .padding(24.dp)
    )
}

@Composable
fun NewsLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator()
}