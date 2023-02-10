package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDescription
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskFactory
import com.stringconcat.todo.cleanarchitecture.usecase.CreateNewTaskUsecase

class CreateNewTaskScenario(
    private val taskFactory: TaskFactory
) : CreateNewTaskUsecase{
    override fun invoke(description: TaskDescription, deadline: TaskDeadline) =
        taskFactory.createTask(description, deadline)
}