package com.stringconcat.todo.cleanarchitecture.usecase.access

import com.stringconcat.todo.cleanarchitecture.domain.task.Task

interface PersistTask {
    fun persist(task: Task)
}