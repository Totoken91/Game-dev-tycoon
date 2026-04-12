package com.gamedevstudio.domain.model

data class TimeState(
    val week: Int = 1,
    val month: Int = 1,
    val year: Int = 1985,
    val tickSpeed: Speed = Speed.X1,
    val isPaused: Boolean = false,
    val totalWeeks: Long = 0
)

enum class Speed(val ms: Long) {
    X1(2000),
    X2(1000),
    X4(500),
    X8(250)  // Premium only
}
