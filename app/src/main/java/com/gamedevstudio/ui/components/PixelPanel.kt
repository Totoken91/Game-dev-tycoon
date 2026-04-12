package com.gamedevstudio.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun PixelPanel(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    // TODO: Implement pixel-art styled panel
    // - Windows 95 / Mac Classic style window chrome
    // - Title bar with close/minimize buttons
    // - Sharp corners, 2px border
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RectangleShape,
        color = PixelColors.Panel,
        border = BorderStroke(2.dp, PixelColors.PanelLight)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            content = content
        )
    }
}
