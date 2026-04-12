package com.gamedevstudio.ui.screens.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gamedevstudio.ui.components.PixelPanel
import com.gamedevstudio.ui.components.PixelProgressBar
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun ProjectPanel(
    modifier: Modifier = Modifier
) {
    // TODO: Show active project info or "Start New Project" button
    // - Project title, genre, theme
    // - Current phase (Planning/Alpha/Beta/Testing/Gold)
    // - Progress bar
    // - Quality indicator
    // - Bug count
    // - When complete: review scores and sales
    PixelPanel(modifier = modifier) {
        Text(
            text = "No active project",
            color = PixelColors.TextSecondary,
            modifier = Modifier.padding(8.dp)
        )
        PixelProgressBar(
            progress = 0f,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
