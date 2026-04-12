package com.gamedevstudio.ui.screens.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun ProjectScreen(
    modifier: Modifier = Modifier
) {
    // TODO: Implement project development screen
    // - Create new project (select genre + theme + title + scope)
    // - Active project details (phase, progress, quality, bugs)
    // - History of completed projects with review scores and sales
    // - Sequel creation option
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Project Development", color = PixelColors.TextSecondary)
    }
}
