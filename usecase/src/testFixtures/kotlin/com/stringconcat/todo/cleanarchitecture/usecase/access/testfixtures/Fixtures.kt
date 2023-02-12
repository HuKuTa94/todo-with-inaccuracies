package com.stringconcat.todo.cleanarchitecture.usecase.access.testfixtures

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.usecase.access.PersistTask

fun persistTask() = object : PersistTask {
    override fun persist(task: Task) {}
}