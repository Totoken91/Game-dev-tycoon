package com.gamedevstudio.domain.model

data class Staff(
    val id: String,
    val name: String,
    val role: StaffRole,
    val level: Int = 1,
    val xp: Long = 0,
    val salary: Int,
    val stats: StaffStats,
    val traits: List<Trait> = emptyList(),
    val morale: Int = 75,
    val fatigue: Int = 0,
    val specialization: Genre? = null
)

data class StaffStats(
    val programming: Int = 0,
    val art: Int = 0,
    val sound: Int = 0,
    val design: Int = 0,
    val writing: Int = 0,
    val marketing: Int = 0
)

enum class StaffRole {
    PROGRAMMER,
    ARTIST,
    SOUND_DESIGNER,
    GAME_DESIGNER,
    WRITER,
    MARKETER,
    PRODUCER  // Unlocked via research
}

enum class Trait {
    WORKAHOLIC,
    CREATIVE,
    PERFECTIONIST,
    SPEEDRUNNER,
    MENTOR,
    DIVA,
    TEAM_PLAYER,
    NIGHT_OWL,
    INNOVATOR,
    VETERAN,
    BUDGET_GURU,
    HYPE_MACHINE
    // TODO: Implement trait effect calculations
}
