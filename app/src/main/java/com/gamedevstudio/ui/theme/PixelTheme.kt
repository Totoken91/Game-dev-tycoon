package com.gamedevstudio.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val PixelColorScheme = darkColorScheme(
    primary = PixelColors.Primary,
    secondary = PixelColors.Accent,
    background = PixelColors.Background,
    surface = PixelColors.Panel,
    onPrimary = PixelColors.TextPrimary,
    onSecondary = PixelColors.TextPrimary,
    onBackground = PixelColors.TextPrimary,
    onSurface = PixelColors.TextPrimary,
    error = PixelColors.Error,
    onError = PixelColors.TextPrimary
)

@Composable
fun PixelTheme(
    content: @Composable () -> Unit
) {
    // TODO: Add pixelated rendering via Compose Canvas
    // TODO: Override Material 3 shapes for pixel-art style (sharp corners)
    // TODO: Add CompositionLocal for game-specific theme values
    MaterialTheme(
        colorScheme = PixelColorScheme,
        typography = PixelTypography.typography,
        content = content
    )
}
