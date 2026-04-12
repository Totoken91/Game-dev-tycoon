package com.gamedevstudio.domain.engine

import com.gamedevstudio.domain.model.TimeState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimeManager @Inject constructor() {

    // TODO: Implement week/month/year conversion and advancement
    // 1 tick = 1 in-game week
    // 4 weeks = 1 month, 12 months = 1 year
    // Start: Week 1, January 1985

    fun advanceWeek(state: TimeState): TimeState {
        // TODO: Increment week, handle month/year rollover
        return state
    }

    fun getMonthName(month: Int): String {
        // TODO: Return localized month name
        return ""
    }

    fun getFormattedDate(state: TimeState): String {
        // TODO: Format as "Jan 1985" etc.
        return ""
    }
}
