package com.stringconcat.todo.cleanarchitecture.task

interface PriorityProvider {
    fun calculatePriority(task: Task): Task.Priority
}