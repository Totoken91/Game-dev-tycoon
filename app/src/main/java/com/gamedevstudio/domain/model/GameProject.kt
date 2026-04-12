package com.gamedevstudio.domain.model

data class GameProject(
    val id: String,
    val title: String,
    val genre: Genre,
    val theme: Theme,
    val scope: ProjectScope = ProjectScope.MINI,
    val phase: DevPhase = DevPhase.PLANNING,
    val progress: Float = 0f,
    val quality: Float = 0f,
    val bugs: Int = 0,
    val weeksElapsed: Int = 0,
    val totalWeeks: Int = 0,
    val isComplete: Boolean = false,
    val reviewScore: Float? = null
    // TODO: Add assigned staff, crunch mode, sequel info, sales data
)

enum class ProjectScope(val minWeeks: Int, val maxWeeks: Int, val minDevs: Int, val maxDevs: Int) {
    MINI(8, 12, 1, 2),
    STANDARD(16, 26, 3, 5),
    MAJOR(30, 52, 5, 10),
    AAA(52, 104, 10, 20)
}

enum class DevPhase(val timePercent: Float) {
    PLANNING(0.10f),
    ALPHA(0.40f),
    BETA(0.30f),
    TESTING(0.15f),
    GOLD(0.05f)
}
