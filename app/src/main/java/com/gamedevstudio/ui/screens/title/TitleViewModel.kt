package com.gamedevstudio.ui.screens.title

import androidx.lifecycle.ViewModel
import com.gamedevstudio.data.repository.GameRepository
import com.gamedevstudio.domain.usecase.StartGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TitleViewModel @Inject constructor(
    private val startGameUseCase: StartGameUseCase,
    private val repository: GameRepository
) : ViewModel() {

    // TODO: Check if a save game exists (for Continue button state)
    // TODO: Handle "New Game" — prompt for studio name, then initialize game
    // TODO: Handle "Continue" — load saved game state from Room DB
    // TODO: Expose UI state as StateFlow

    suspend fun startNewGame(studioName: String) {
        // TODO: Call startGameUseCase and navigate to game screen
        startGameUseCase(studioName)
    }

    fun hasSaveGame(): Boolean {
        // TODO: Check Room DB for existing save
        return false
    }
}
