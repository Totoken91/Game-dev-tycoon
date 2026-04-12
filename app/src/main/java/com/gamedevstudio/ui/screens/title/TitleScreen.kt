package com.gamedevstudio.ui.screens.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gamedevstudio.R
import com.gamedevstudio.ui.components.PixelButton
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun TitleScreen(
    onNewGame: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: Implement title screen with:
    // - Animated pixel art logo
    // - "New Game" button
    // - "Continue" button (enabled only if save exists)
    // - Settings button
    // - Chiptune music playing
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = PixelColors.TextHighlight
        )
        PixelButton(
            text = stringResource(R.string.title_new_game),
            onClick = onNewGame,
            modifier = Modifier.padding(top = 32.dp)
        )
        PixelButton(
            text = stringResource(R.string.title_continue),
            onClick = onContinue,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
