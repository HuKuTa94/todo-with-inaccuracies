package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.usecase.GetImportantTasksUseCase
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksByPriority

class GetImportantTasksScenario(
    private val findTasksByPriority: FindTasksByPriority
) : GetImportantTasksUseCase {
    override fun invoke() = findTasksByPriority.find(Task.Priority.HIGH)
}