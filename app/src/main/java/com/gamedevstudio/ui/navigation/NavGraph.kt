package com.gamedevstudio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gamedevstudio.ui.screens.game.GameScreen
import com.gamedevstudio.ui.screens.title.TitleScreen

object Routes {
    const val TITLE = "title"
    const val GAME = "game"
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.TITLE
    ) {
        composable(Routes.TITLE) {
            TitleScreen(
                onNewGame = {
                    // TODO: Show studio name dialog, then navigate
                    navController.navigate(Routes.GAME) {
                        popUpTo(Routes.TITLE) { inclusive = true }
                    }
                },
                onContinue = {
                    // TODO: Load saved game, then navigate
                    navController.navigate(Routes.GAME) {
                        popUpTo(Routes.TITLE) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.GAME) {
            GameScreen()
        }
    }
    // TODO: Add navigation transitions (pixel-art style wipe/fade)
}
