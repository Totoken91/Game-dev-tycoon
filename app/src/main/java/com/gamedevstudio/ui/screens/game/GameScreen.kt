package com.gamedevstudio.ui.screens.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun GameScreen(
    modifier: Modifier = Modifier
) {
    // TODO: Implement main game screen layout:
    // ┌─────────────────────┐
    // │ TopBar (cash + date) │
    // ├─────────────────────┤
    // │ StudioView (office) │
    // ├─────────────────────┤
    // │ ProjectPanel (active)│
    // ├─────────────────────┤
    // │ BottomNav            │
    // └─────────────────────┘
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNav(selectedTab = 0, onTabSelected = {}) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            StudioView(modifier = Modifier.weight(1f))
            ProjectPanel()
        }
    }
}
