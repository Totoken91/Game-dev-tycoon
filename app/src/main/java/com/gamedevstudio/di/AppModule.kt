package com.gamedevstudio.di

import android.content.Context
import com.gamedevstudio.data.db.GameDatabase
import com.gamedevstudio.data.db.dao.FinanceDao
import com.gamedevstudio.data.db.dao.ProjectDao
import com.gamedevstudio.data.db.dao.StaffDao
import com.gamedevstudio.data.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext context: Context): GameDatabase {
        // TODO: Build Room database instance
        return GameDatabase.create(context)
    }

    @Provides
    fun provideStaffDao(db: GameDatabase): StaffDao = db.staffDao()

    @Provides
    fun provideProjectDao(db: GameDatabase): ProjectDao = db.projectDao()

    @Provides
    fun provideFinanceDao(db: GameDatabase): FinanceDao = db.financeDao()

    @Provides
    @Singleton
    fun provideGameRepository(
        staffDao: StaffDao,
        projectDao: ProjectDao,
        financeDao: FinanceDao
    ): GameRepository {
        // TODO: Wire up repository with DAOs
        return GameRepository(staffDao, projectDao, financeDao)
    }
}
