package com.gamedevstudio.data.repository

import com.gamedevstudio.data.db.dao.FinanceDao
import com.gamedevstudio.data.db.dao.ProjectDao
import com.gamedevstudio.data.db.dao.StaffDao
import com.gamedevstudio.data.db.entities.FinanceEntity
import com.gamedevstudio.data.db.entities.ProjectEntity
import com.gamedevstudio.data.db.entities.StaffEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val staffDao: StaffDao,
    private val projectDao: ProjectDao,
    private val financeDao: FinanceDao
) {
    // Staff
    fun getAllStaff(): Flow<List<StaffEntity>> = staffDao.getAllStaff()
    suspend fun insertStaff(staff: StaffEntity) = staffDao.insertStaff(staff)
    suspend fun updateStaff(staff: StaffEntity) = staffDao.updateStaff(staff)

    // Projects
    fun getActiveProject(): Flow<ProjectEntity?> = projectDao.getActiveProject()
    fun getAllProjects(): Flow<List<ProjectEntity>> = projectDao.getAllProjects()
    suspend fun insertProject(project: ProjectEntity) = projectDao.insertProject(project)
    suspend fun updateProject(project: ProjectEntity) = projectDao.updateProject(project)

    // Finance
    fun getFinanceState(): Flow<FinanceEntity?> = financeDao.getFinanceState()
    suspend fun insertFinance(finance: FinanceEntity) = financeDao.insertFinance(finance)
    suspend fun updateFinance(finance: FinanceEntity) = financeDao.updateFinance(finance)

    // TODO: Add save/load game state, export/import JSON backup
    // TODO: Add transaction methods for complex operations
}
