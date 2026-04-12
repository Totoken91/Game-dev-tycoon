package com.gamedevstudio.domain.model

data class FinanceState(
    val cash: Long = 50_000,
    val totalRevenue: Long = 0,
    val totalExpenses: Long = 0,
    val weeklyRevenue: Long = 0,
    val weeklyExpenses: Long = 0
    // TODO: Add revenue breakdown, expense categories, sales history per game
)
