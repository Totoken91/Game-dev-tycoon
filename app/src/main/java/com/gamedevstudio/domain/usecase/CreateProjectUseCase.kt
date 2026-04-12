package com.gamedevstudio.domain.usecase

import com.gamedevstudio.data.repository.GameRepository
import com.gamedevstudio.domain.model.GameProject
import com.gamedevstudio.domain.model.Genre
import com.gamedevstudio.domain.model.Theme
import com.gamedevstudio.domain.systems.ProjectSystem
import javax.inject.Inject

class CreateProjectUseCase @Inject constructor(
    private val repository: GameRepository,
    private val projectSystem: ProjectSystem
) {
    // TODO: Create a new game project
    // - Validate: no active project in progress
    // - Validate: sufficient funds for scope
    // - Create project with genre + theme + title
    // - Save to Room DB
    // - Return the created project

    suspend operator fun invoke(title: String, genre: Genre, theme: Theme): GameProject {
        // TODO: Implement project creation flow
        return projectSystem.createProject(title, genre, theme)
    }
}
