package com.gamedevstudio.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "finance")
data class FinanceEntity(
    @PrimaryKey val id: Int = 1,
    val cash: Long,
    val totalRevenue: Long,
    val totalExpenses: Long,
    val weeklyRevenue: Long,
    val weeklyExpenses: Long
    // TODO: Add revenue history, expense breakdown, sales tracking
)
