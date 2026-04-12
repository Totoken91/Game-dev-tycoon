package com.gamedevstudio.ui.screens.research

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun ResearchScreen(
    modifier: Modifier = Modifier
) {
    // TODO: Implement tech tree / research screen
    // - Research nodes: unlock genres, platforms, features, office upgrades
    // - 25+ research nodes
    // - Cost in cash and time to research
    // - Visual tech tree with connections
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Research & Tech Tree", color = PixelColors.TextSecondary)
    }
}
