package com.gamedevstudio.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun PixelProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = PixelColors.ProgressBar,
    backgroundColor: Color = PixelColors.PanelLight
) {
    // TODO: Implement pixel-art styled progress bar
    // - Segmented/chunky fill style
    // - Optional label overlay (percentage or phase name)
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
    ) {
        // Background
        drawRect(
            color = backgroundColor,
            size = size
        )
        // Fill
        drawRect(
            color = color,
            size = Size(size.width * progress.coerceIn(0f, 1f), size.height)
        )
        // Border
        drawRect(
            color = PixelColors.TextSecondary,
            topLeft = Offset.Zero,
            size = size,
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f)
        )
    }
}
