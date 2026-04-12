package com.gamedevstudio.domain.systems

import com.gamedevstudio.domain.model.FinanceState
import com.gamedevstudio.domain.model.GameProject
import com.gamedevstudio.domain.model.Staff
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FinanceSystem @Inject constructor() {

    // TODO: Implement financial calculations
    // Starting cash: $50,000
    // Base sales per review point: 5,000 units/week
    // Sales decay: -8%/week
    // Game price: $29.99
    // Salary payments: monthly (week 4 of each month)

    fun calculateWeeklySales(
        project: GameProject,
        weeksAfterLaunch: Int,
        hype: Int
    ): Long {
        // TODO: sales = baseSales × exp(-0.08 × N) × platformShare × hype/50 × wordOfMouth
        // TODO: Apply launch week (×3), Christmas (×1.5), sale (×2) multipliers
        return 0L
    }

    fun paySalaries(staff: List<Staff>, finance: FinanceState): FinanceState {
        // TODO: Deduct total salaries from cash
        return finance
    }

    fun tickFinance(finance: FinanceState, revenue: Long, expenses: Long): FinanceState {
        // TODO: Update cash, weekly/total revenue and expenses
        return finance
    }
}
