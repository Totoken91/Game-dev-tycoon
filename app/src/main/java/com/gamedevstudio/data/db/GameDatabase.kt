package com.gamedevstudio.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gamedevstudio.data.db.dao.FinanceDao
import com.gamedevstudio.data.db.dao.ProjectDao
import com.gamedevstudio.data.db.dao.StaffDao
import com.gamedevstudio.data.db.entities.FinanceEntity
import com.gamedevstudio.data.db.entities.ProjectEntity
import com.gamedevstudio.data.db.entities.StaffEntity

@Database(
    entities = [
        StaffEntity::class,
        ProjectEntity::class,
        FinanceEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class GameDatabase : RoomDatabase() {

    abstract fun staffDao(): StaffDao
    abstract fun projectDao(): ProjectDao
    abstract fun financeDao(): FinanceDao

    companion object {
        fun create(context: Context): GameDatabase {
            return Room.databaseBuilder(
                context,
                GameDatabase::class.java,
                "game_dev_studio.db"
            ).build()
        }
    }
}
