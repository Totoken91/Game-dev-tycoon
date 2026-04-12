package com.gamedevstudio.domain.engine

import com.gamedevstudio.domain.model.Speed
import com.gamedevstudio.domain.model.TimeState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameLoop @Inject constructor(
    private val timeManager: TimeManager
) {
    // TODO: Implement coroutine-based game loop
    // Main loop (per tick):
    // 1. Advance time (week++)
    // 2. Update active projects (dev progression)
    // 3. Calculate passive revenue (ongoing sales)
    // 4. Pay salaries (monthly, week 4 of each month)
    // 5. Update staff (XP, morale, fatigue)
    // 6. Check random events
    // 7. Update fanbase
    // 8. Check milestones / unlocks
    // 9. Trigger reviews if a game just shipped
    // 10. Auto-save (every 4 weeks)

    // TODO: Expose StateFlow<TimeState> for UI observation
    // TODO: Implement pause/resume/speed change
    // TODO: Implement fast-forward for offline catch-up (max 720 ticks)

    fun start() {
        // TODO: Launch coroutine loop with delay(speed.ms)
    }

    fun pause() {
        // TODO: Set isPaused = true
    }

    fun resume() {
        // TODO: Set isPaused = false
    }

    fun setSpeed(speed: Speed) {
        // TODO: Update tick interval
    }

    fun fastForward(ticks: Long) {
        // TODO: Process missed ticks for offline progress
    }
}
