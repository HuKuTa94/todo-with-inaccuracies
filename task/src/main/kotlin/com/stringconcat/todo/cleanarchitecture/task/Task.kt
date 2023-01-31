package com.stringconcat.todo.cleanarchitecture.task

data class Task internal constructor(
    val id: TaskId = TaskId(),
    val description: TaskDescription,
    val deadline: TaskDeadline,
    var priority: Priority = Priority.NEED_CALCULATION
) {
    enum class Priority {
        NEED_CALCULATION, LOW, MIDDLE, HIGH
    }

    fun changePriority(calculatedPriority: Priority) {
        priority = calculatedPriority
    }
}
