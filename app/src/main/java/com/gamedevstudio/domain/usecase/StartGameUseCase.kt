package com.gamedevstudio.domain.usecase

import com.gamedevstudio.data.repository.GameRepository
import javax.inject.Inject

class StartGameUseCase @Inject constructor(
    private val repository: GameRepository
) {
    // TODO: Initialize new game state
    // - Set starting cash to $50,000
    // - Create the player's initial developer (staff member)
    // - Initialize time to Week 1, January 1985
    // - Save initial state to Room DB
    // - Start the game loop

    suspend operator fun invoke(studioName: String) {
        // TODO: Implement game initialization
    }
}
