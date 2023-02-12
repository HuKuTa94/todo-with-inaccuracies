package com.stringconcat.todo.cleanarchitecture.usecase

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import java.time.LocalDateTime

interface GetCloseToDeadlineTasksUseCase {
    fun invoke(deadline : LocalDateTime): List<Task>
}