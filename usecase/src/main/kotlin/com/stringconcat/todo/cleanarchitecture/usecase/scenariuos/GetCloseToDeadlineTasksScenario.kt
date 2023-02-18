package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.usecase.GetCloseToDeadlineTasksUseCase
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksByDeadlineRange
import java.time.LocalDateTime

/**
 * Finds tasks that are before 3 days to deadline
 */
class GetCloseToDeadlineTasksScenario(
    private val findTasksByDeadlineRange: FindTasksByDeadlineRange
) : GetCloseToDeadlineTasksUseCase {

    override fun invoke(deadline: LocalDateTime): List<Task> {
        val from = deadline.minusDays(DAYS_TO_DEADLINE)
        return findTasksByDeadlineRange.find(from, deadline)
    }

    companion object {
        private const val DAYS_TO_DEADLINE = 3L
    }
}