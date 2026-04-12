package com.gamedevstudio.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// TODO: Load "Press Start 2P" bitmap pixel font from assets
// TODO: Set up fallback to Noto Sans Mono for CJK characters

object PixelTypography {

    // TODO: Replace with actual pixel font family
    private val PixelFont = FontFamily.Monospace

    val typography = Typography(
        displayLarge = TextStyle(
            fontFamily = PixelFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = PixelColors.TextPrimary
        ),
        titleLarge = TextStyle(
            fontFamily = PixelFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = PixelColors.TextPrimary
        ),
        titleMedium = TextStyle(
            fontFamily = PixelFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = PixelColors.TextPrimary
        ),
        bodyLarge = TextStyle(
            fontFamily = PixelFont,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = PixelColors.TextPrimary
        ),
        bodyMedium = TextStyle(
            fontFamily = PixelFont,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = PixelColors.TextSecondary
        ),
        labelLarge = TextStyle(
            fontFamily = PixelFont,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            color = PixelColors.TextPrimary
        )
    )
}
