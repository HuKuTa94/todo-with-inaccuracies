package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.usecase.GetCloseToDeadlineTasksUseCase
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksCloseToDeadline
import java.time.LocalDateTime

class GetCloseToDeadlineTasksScenario(
    private val findTasksCloseToDeadline: FindTasksCloseToDeadline
) : GetCloseToDeadlineTasksUseCase {
    override fun invoke(deadline: LocalDateTime) = findTasksCloseToDeadline.find(deadline)
}