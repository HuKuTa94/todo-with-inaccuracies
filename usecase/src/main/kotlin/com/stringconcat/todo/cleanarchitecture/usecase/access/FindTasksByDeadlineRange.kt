package com.stringconcat.todo.cleanarchitecture.usecase.access

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import java.time.LocalDateTime

interface FindTasksByDeadlineRange {
    fun find(from: LocalDateTime, to: LocalDateTime): List<Task>
}