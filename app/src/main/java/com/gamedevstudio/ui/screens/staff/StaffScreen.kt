package com.gamedevstudio.ui.screens.staff

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gamedevstudio.ui.theme.PixelColors

@Composable
fun StaffScreen(
    modifier: Modifier = Modifier
) {
    // TODO: Implement staff management screen
    // - List of hired staff with stats, level, morale, fatigue
    // - Hire new staff (candidate pool refreshes monthly)
    // - Fire staff
    // - Send staff to training
    // - View staff details (stats, traits, specialization)
    // - Assign staff to active project
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Staff Management", color = PixelColors.TextSecondary)
    }
}
