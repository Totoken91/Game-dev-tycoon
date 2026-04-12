package com.gamedevstudio.ui.screens.shop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun ShopScreen(
    modifier: Modifier = Modifier
) {
    // TODO: Implement IAP store screen
    // - Premium Lite ($4.99): Remove ads + x8 speed
    // - Premium Full ($9.99): All Lite + themes + office skins + badge
    // - Collector ($14.99): All Full + sandbox + legendary traits
    // - Cosmetic packs: office skins, staff sprites, music packs, UI themes
    // - Season pass
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Shop", color = PixelColors.TextSecondary)
    }
}
