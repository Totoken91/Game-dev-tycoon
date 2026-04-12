package com.gamedevstudio.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff")
data class StaffEntity(
    @PrimaryKey val id: String,
    val name: String,
    val role: String,
    val level: Int,
    val xp: Long,
    val salary: Int,
    val programming: Int,
    val art: Int,
    val sound: Int,
    val design: Int,
    val writing: Int,
    val marketing: Int,
    val morale: Int,
    val fatigue: Int,
    val specialization: String?
    // TODO: Add traits list (stored as JSON or separate table)
)
