package com.gamedevstudio.domain.data

object Balance {

    // --- Time ---
    const val START_YEAR = 1985
    const val WEEKS_PER_MONTH = 4
    const val MONTHS_PER_YEAR = 12
    const val TICK_DURATION_MS = 2000L // Base: 2s = 1 week
    const val MAX_OFFLINE_TICKS = 720L // ~1 in-game month

    // --- Finance ---
    const val STARTING_CASH = 50_000L
    const val GAME_PRICE = 29.99f
    const val BASE_SALES_PER_REVIEW_POINT = 5_000
    const val SALES_DECAY_RATE = 0.08f // -8%/week
    const val LAUNCH_WEEK_MULTIPLIER = 3.0f
    const val CHRISTMAS_MULTIPLIER = 1.5f
    const val SALE_MULTIPLIER = 2.0f
    const val CLASSIC_FLOOR = 0.05f // Long tail minimum

    // --- Staff ---
    const val DEFAULT_MORALE = 75
    const val MAX_MORALE = 100
    const val FATIGUE_PER_WEEK = 3
    const val CRUNCH_FATIGUE_PER_WEEK = 10
    const val ILLNESS_THRESHOLD = 80
    const val QUIT_THRESHOLD = 90
    const val VACATION_DURATION_WEEKS = 2
    const val HIRING_COST_MULTIPLIER = 2 // 2x weekly salary

    // --- Staff Capacity ---
    const val CAPACITY_GARAGE = 4
    const val CAPACITY_SMALL = 8
    const val CAPACITY_MEDIUM = 12
    const val CAPACITY_LARGE = 16
    const val CAPACITY_CAMPUS = 20

    // --- Project ---
    const val CRUNCH_QUALITY_PENALTY = 0.85f // -15%

    // --- Hype ---
    const val MAX_HYPE = 100
    const val HYPE_DECAY_PER_WEEK = 3

    // TODO: Add marketing costs, training costs, console dev kit costs
    // TODO: Add platform market shares by era
    // TODO: Add reputation thresholds for candidate quality
}
