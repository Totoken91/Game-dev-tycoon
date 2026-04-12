package com.gamedevstudio.ui.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    // TODO: Display cash, date (month/year), and speed controls
    // Layout: | $125K | Jun 95 | ▶ |
    // TODO: Wire up to GameViewModel state
    Surface(
        color = PixelColors.Panel,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$50,000", color = PixelColors.TextHighlight)
            Text(text = "Jan 1985", color = PixelColors.TextPrimary)
            Text(text = "▶", color = PixelColors.Primary)
        }
    }
}
