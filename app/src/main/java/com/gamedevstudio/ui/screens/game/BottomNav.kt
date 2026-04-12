package com.gamedevstudio.ui.screens.game

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gamedevstudio.R
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun BottomNav(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: Implement bottom navigation with pixel art icons
    // Tabs: Staff | Project | Marketing | Research | Finance
    // Optimized for one-handed play (thumb reach)
    val tabs = listOf(
        R.string.nav_staff,
        R.string.nav_project,
        R.string.nav_marketing,
        R.string.nav_research,
        R.string.nav_finance
    )

    NavigationBar(
        modifier = modifier,
        containerColor = PixelColors.Panel
    ) {
        tabs.forEachIndexed { index, titleRes ->
            NavigationBarItem(
                icon = { /* TODO: Add pixel art icons */ },
                label = { Text(stringResource(titleRes)) },
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PixelColors.Primary,
                    selectedTextColor = PixelColors.Primary,
                    unselectedIconColor = PixelColors.TextSecondary,
                    unselectedTextColor = PixelColors.TextSecondary,
                    indicatorColor = PixelColors.PanelLight
                )
            )
        }
    }
}
