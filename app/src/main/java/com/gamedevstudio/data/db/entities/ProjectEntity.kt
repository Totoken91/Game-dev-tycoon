package com.gamedevstudio.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey val id: String,
    val title: String,
    val genre: String,
    val theme: String,
    val scope: String,
    val phase: String,
    val progress: Float,
    val quality: Float,
    val bugs: Int,
    val weeksElapsed: Int,
    val totalWeeks: Int,
    val isComplete: Boolean,
    val reviewScore: Float?
    // TODO: Add assigned staff IDs, marketing hype, sales data
)
