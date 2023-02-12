package com.stringconcat.todo.cleanarchitecture.usecase

import com.stringconcat.todo.cleanarchitecture.domain.task.Task

interface GetImportantTasksUseCase {
    fun invoke() : List<Task>
}