package com.gamedevstudio.ui.theme

import androidx.compose.ui.graphics.Color

// Pixel art 32-color palette inspired by Endesga 32
object PixelColors {
    // Backgrounds
    val Background = Color(0xFF0A0A12)
    val Panel = Color(0xFF14151F)
    val PanelLight = Color(0xFF1E1F2E)

    // Primary
    val Primary = Color(0xFF4A9EE5)
    val PrimaryDark = Color(0xFF2A6DB0)
    val Accent = Color(0xFFE5A44A)

    // Text
    val TextPrimary = Color(0xFFE0E0E0)
    val TextSecondary = Color(0xFF8A8A9A)
    val TextHighlight = Color(0xFFFFD700)

    // Status
    val Success = Color(0xFF4AE54A)
    val Warning = Color(0xFFE5A44A)
    val Error = Color(0xFFE54A4A)

    // Game-specific
    val MoraleHigh = Color(0xFF4AE54A)
    val MoraleLow = Color(0xFFE54A4A)
    val XpBar = Color(0xFF4A9EE5)
    val ProgressBar = Color(0xFFE5A44A)

    // TODO: Complete 32-color palette per Endesga 32 spec
    // TODO: Add era-specific accent colors (8-bit, 16-bit, 3D, modern)
}
