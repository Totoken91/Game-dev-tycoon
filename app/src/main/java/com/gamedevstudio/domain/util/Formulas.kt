package com.gamedevstudio.domain.util

import com.gamedevstudio.domain.model.Genre
import com.gamedevstudio.domain.model.StaffStats
import com.gamedevstudio.domain.model.Theme
import kotlin.math.exp
import kotlin.math.max

object Formulas {

    // TODO: Implement all game formulas here

    // --- Genre Stat Weights ---
    // Each genre weights staff stats differently for quality calculation
    // ACTION: prog=0.3, art=0.2, design=0.3, writing=0.1, sound=0.1
    // PUZZLE: prog=0.25, art=0.15, design=0.4, writing=0.1, sound=0.1
    // ADVENTURE: prog=0.15, art=0.2, design=0.2, writing=0.35, sound=0.1

    data class StatWeights(
        val programming: Float,
        val art: Float,
        val design: Float,
        val writing: Float,
        val sound: Float
    )

    fun getGenreWeights(genre: Genre): StatWeights {
        // TODO: Return weights for all 20 genres
        return StatWeights(0.2f, 0.2f, 0.2f, 0.2f, 0.2f)
    }

    // --- Genre × Theme Synergy ---
    // S=1.30, A=1.15, B=1.0, C=0.90, D=0.80
    fun getSynergy(genre: Genre, theme: Theme): Float {
        // TODO: Implement full 20×21 synergy matrix
        // MVP synergy:
        // ACTION+MODERN=1.15, ACTION+FANTASY=1.0, ACTION+SPACE=1.15
        // PUZZLE+MODERN=1.0, PUZZLE+FANTASY=0.9, PUZZLE+SPACE=1.15
        // ADVENTURE+MODERN=1.0, ADVENTURE+FANTASY=1.3, ADVENTURE+SPACE=1.15
        return 1.0f
    }

    // --- Quality Calculation ---
    fun calculateQuality(
        teamStats: StaffStats,
        genre: Genre,
        theme: Theme,
        avgMorale: Float,
        isCrunch: Boolean,
        bugs: Int,
        sequelBonus: Float
    ): Float {
        // TODO: Implement quality formula
        // quality = (raw / maxScore * 10 * synergy * morale * crunch * bugs * sequel)
        return 0f
    }

    // --- Sales Calculation ---
    fun calculateWeeklySales(
        reviewScore: Float,
        weeksAfterLaunch: Int,
        hype: Int,
        platformShare: Float
    ): Long {
        // TODO: Implement sales formula
        // baseSales = reviewScore * 5000
        // sales = baseSales * exp(-0.08 * N) * platformShare * (hype/50) * wordOfMouth
        return 0L
    }

    // --- XP Calculation ---
    fun xpForLevel(level: Int): Long {
        // TODO: Define XP curve
        return 0L
    }
}
