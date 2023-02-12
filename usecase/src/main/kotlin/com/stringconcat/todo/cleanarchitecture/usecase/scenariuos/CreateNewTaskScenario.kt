package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDescription
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskFactory
import com.stringconcat.todo.cleanarchitecture.usecase.CreateNewTaskUseCase
import com.stringconcat.todo.cleanarchitecture.usecase.access.PersistTask

class CreateNewTaskScenario(
    private val taskFactory: TaskFactory,
    private val persistTask: PersistTask
) : CreateNewTaskUseCase{
    override fun invoke(description: TaskDescription, deadline: TaskDeadline): Task {
        val task = taskFactory.createTask(description, deadline)
        persistTask.persist(task)
        return task
    }
}