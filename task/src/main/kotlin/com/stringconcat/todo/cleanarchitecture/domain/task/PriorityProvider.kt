package com.stringconcat.todo.cleanarchitecture.domain.task

interface PriorityProvider {
    fun calculatePriority(task: Task): Task.Priority
}