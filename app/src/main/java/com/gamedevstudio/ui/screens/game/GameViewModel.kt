package com.gamedevstudio.ui.screens.game

import androidx.lifecycle.ViewModel
import com.gamedevstudio.data.repository.GameRepository
import com.gamedevstudio.domain.engine.GameLoop
import com.gamedevstudio.domain.usecase.CreateProjectUseCase
import com.gamedevstudio.domain.usecase.TickUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameLoop: GameLoop,
    private val tickUseCase: TickUseCase,
    private val createProjectUseCase: CreateProjectUseCase,
    private val repository: GameRepository
) : ViewModel() {

    // TODO: Expose game state as StateFlow for Compose observation:
    // - TimeState (week, month, year, speed, paused)
    // - FinanceState (cash, revenue, expenses)
    // - Active project progress
    // - Staff list
    // - Current reviews / sales

    // TODO: Handle user actions:
    // - Pause / resume / change speed
    // - Start new project (genre + theme + title)
    // - View project progress
    // - View finance overview

    // TODO: Collect game loop ticks and update UI state
    // TODO: Handle offline catch-up on ViewModel init
}
