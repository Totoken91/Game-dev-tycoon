package com.gamedevstudio.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.gamedevstudio.data.db.entities.FinanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {

    @Query("SELECT * FROM finance WHERE id = 1")
    fun getFinanceState(): Flow<FinanceEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFinance(finance: FinanceEntity)

    @Update
    suspend fun updateFinance(finance: FinanceEntity)

    // TODO: Add queries for revenue history, expense breakdown
}
