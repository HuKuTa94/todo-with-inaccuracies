package com.stringconcat.todo.cleanarchitecture.usecase

import com.stringconcat.todo.cleanarchitecture.task.Task
import com.stringconcat.todo.cleanarchitecture.task.TaskDeadline
import com.stringconcat.todo.cleanarchitecture.task.TaskDescription

interface CreateNewTaskUsecase {
    fun invoke(description: TaskDescription, deadline: TaskDeadline): Task
}