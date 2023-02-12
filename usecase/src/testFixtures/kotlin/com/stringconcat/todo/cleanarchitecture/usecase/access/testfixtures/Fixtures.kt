package com.stringconcat.todo.cleanarchitecture.usecase.access.testfixtures

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.*
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksByPriority
import com.stringconcat.todo.cleanarchitecture.usecase.access.PersistTask

fun persistTask() = object : PersistTask {
    override fun persist(task: Task) {}
}

fun findTasksByPriority() = object : FindTasksByPriority {
    val taskDescription = taskDescription()
    val taskDeadline = taskDeadline()

    override fun find(priority: Task.Priority) = when (priority) {
        Task.Priority.LOW -> listOf(
            taskFactory(lowPriorityProvider()).createTask(taskDescription, taskDeadline)
        )
        Task.Priority.MIDDLE -> listOf(
            taskFactory(middlePriorityProvider()).createTask(taskDescription, taskDeadline)
        )
        Task.Priority.HIGH -> listOf(
            taskFactory(highPriorityProvider()).createTask(taskDescription, taskDeadline)
        )
        else -> emptyList()
    }
}