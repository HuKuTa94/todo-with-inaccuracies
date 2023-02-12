package com.stringconcat.todo.cleanarchitecture.usecase.access

import com.stringconcat.todo.cleanarchitecture.domain.task.Task

interface FindTasksByPriority {
    fun find(priority: Task.Priority): List<Task>
}