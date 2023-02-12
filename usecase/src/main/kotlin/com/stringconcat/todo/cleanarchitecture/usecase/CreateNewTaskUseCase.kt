package com.stringconcat.todo.cleanarchitecture.usecase

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDescription

interface CreateNewTaskUseCase {
    fun invoke(description: TaskDescription, deadline: TaskDeadline): Task
}