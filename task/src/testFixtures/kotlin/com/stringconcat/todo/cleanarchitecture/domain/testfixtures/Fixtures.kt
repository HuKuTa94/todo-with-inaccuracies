package com.stringconcat.todo.cleanarchitecture.domain.testfixtures

import arrow.core.Either
import com.stringconcat.todo.cleanarchitecture.domain.task.*
import java.time.LocalDateTime
import java.util.UUID

const val PACKAGE_DOMAIN_TASK = "com.stringconcat.todo.cleanarchitecture.domain.task"

fun taskDescription(
    description: String = "Some valid description of task"
): TaskDescription {
    val result = TaskDescription.of(description)
    check(result is Either.Right<TaskDescription>)
    return result.value
}

fun taskDeadline(
    deadline: LocalDateTime = LocalDateTime.now().plusMonths(1L)
): TaskDeadline {
    val result = TaskDeadline.of(deadline)
    check(result is Either.Right<TaskDeadline>)
    return result.value
}

fun taskId(id: UUID? = UUID.randomUUID()) = TaskId.of(id)

fun task(
    description: TaskDescription = taskDescription(),
    deadline: TaskDeadline = taskDeadline(),
    priority: Task.Priority = Task.Priority.NEED_CALCULATION
) = taskFactory()
    .createTask(
        description,
        deadline
    ).also {
        it.changePriority(priority)
    }

fun taskFactory(
    priorityProvider: PriorityProvider = lowPriorityProvider()
) = TaskFactory(priorityProvider)

fun lowPriorityProvider() = object : PriorityProvider {
    override fun calculatePriority(task: Task) = Task.Priority.LOW
}

fun middlePriorityProvider() = object : PriorityProvider {
    override fun calculatePriority(task: Task) = Task.Priority.MIDDLE
}

fun highPriorityProvider() = object : PriorityProvider {
    override fun calculatePriority(task: Task) = Task.Priority.HIGH
}