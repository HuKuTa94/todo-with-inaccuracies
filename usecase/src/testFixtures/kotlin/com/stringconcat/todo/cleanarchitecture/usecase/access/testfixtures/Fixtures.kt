package com.stringconcat.todo.cleanarchitecture.usecase.access.testfixtures

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.*
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksByPriority
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksByDeadlineRange
import com.stringconcat.todo.cleanarchitecture.usecase.access.PersistTask
import java.time.LocalDateTime

private val lowPriorityTaskFactory = taskFactory(lowPriorityProvider())
private val middlePriorityTaskFactory = taskFactory(middlePriorityProvider())
private val highPriorityTaskFactory = taskFactory(highPriorityProvider())
private val taskDescription = taskDescription()

fun persistTask() = object : PersistTask {
    override fun persist(task: Task) {}
}

fun findTasksByPriority() = object : FindTasksByPriority {
    val taskDeadline = taskDeadline()

    override fun find(priority: Task.Priority) = when (priority) {
        Task.Priority.LOW -> listOf(
            lowPriorityTaskFactory.createTask(taskDescription, taskDeadline)
        )
        Task.Priority.MIDDLE -> listOf(
            middlePriorityTaskFactory.createTask(taskDescription, taskDeadline)
        )
        Task.Priority.HIGH -> listOf(
            highPriorityTaskFactory.createTask(taskDescription, taskDeadline)
        )
        else -> emptyList()
    }
}

fun findTasksByDeadlineRange() = object : FindTasksByDeadlineRange {
    val taskDescription = taskDescription()

    val tasks = listOf(
        lowPriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(1))),
        lowPriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(2))),
        lowPriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(3))),
        middlePriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(4))),
        middlePriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(5))),
        middlePriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(6))),
        highPriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(7))),
        highPriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(8))),
        highPriorityTaskFactory.createTask(taskDescription, taskDeadline(LocalDateTime.now().plusDays(9))),
    )

    override fun find(from: LocalDateTime, to: LocalDateTime): List<Task> = tasks.filter {
        val taskDeadline = it.deadline.toLocalDateTime()
        taskDeadline.isAfter(from) && taskDeadline.isBefore(to)
    }
}