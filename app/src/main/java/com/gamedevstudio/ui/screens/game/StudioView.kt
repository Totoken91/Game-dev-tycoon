package com.gamedevstudio.ui.screens.game

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun StudioView(
    modifier: Modifier = Modifier
) {
    // TODO: Implement animated studio/office view
    // - Simplified top-down isometric view
    // - Pixel art characters at desks working
    // - Animations: typing bubbles, art stars, music notes
    // - Upgradeable office appearance (garage → campus)
    // - Characters react to events (confetti, rain clouds, etc.)
    Canvas(
        modifier = modifier.fillMaxWidth()
    ) {
        // TODO: Draw office background, furniture, and animated staff sprites
        drawRect(color = PixelColors.Background)
    }
}
