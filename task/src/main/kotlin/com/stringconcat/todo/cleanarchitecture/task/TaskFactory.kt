package com.stringconcat.todo.cleanarchitecture.task

class TaskFactory(
    private val priorityProvider: PriorityProvider
) {
    fun createTask(
        description: TaskDescription,
        deadline: TaskDeadline
    ) = Task(
        description = description,
        deadline = deadline
    ).also { task ->
        val calculatedPriority = priorityProvider.calculatePriority(task)
        task.changePriority(calculatedPriority)
    }
}