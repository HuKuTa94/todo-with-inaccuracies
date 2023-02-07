package com.stringconcat.todo.cleanarchitecture.task

import arrow.core.Either
import java.time.LocalDateTime
import java.util.UUID

fun taskDescription(): TaskDescription {
    val result = TaskDescription.of("Some valid description of task")
    check(result is Either.Right<TaskDescription>)
    return result.value
}

fun taskDeadline(): TaskDeadline {
    val result = TaskDeadline.of(LocalDateTime.now().plusMonths(1L))
    check(result is Either.Right<TaskDeadline>)
    return result.value
}

fun taskId(id: UUID? = UUID.randomUUID()) = TaskId.of(id)

fun task() = taskFactory()
    .createTask(
        taskDescription(),
        taskDeadline()
    )

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