package com.stringconcat.todo.cleanarchitecture.usecase.access

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import java.time.LocalDateTime

/**
 * Finds tasks that are before 3 days to deadline
 */
interface FindTasksCloseToDeadline {
    fun find(deadline: LocalDateTime): List<Task>
}