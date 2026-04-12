package com.gamedevstudio.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun PixelButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    // TODO: Implement pixel-art styled button
    // - Sharp rectangle corners (no rounding)
    // - 2px border in accent color
    // - Pixel font text
    // - Press animation: 2px offset down+right
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RectangleShape,
        border = BorderStroke(2.dp, PixelColors.Primary),
        colors = ButtonDefaults.buttonColors(
            containerColor = PixelColors.Panel,
            contentColor = PixelColors.TextPrimary,
            disabledContainerColor = PixelColors.PanelLight,
            disabledContentColor = PixelColors.TextSecondary
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text)
    }
}
