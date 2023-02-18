package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.usecase.access.testfixtures.findTasksByDeadlineRange
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class GetCloseToDeadlineTasksScenarioTest {
    @Test
    fun `get close to deadline tasks - date is future - should return list task that close to deadline`() {
        // given
        val deadline = LocalDateTime.now().plusDays(3L)

        // when
        val actualTasks = GetCloseToDeadlineTasksScenario(findTasksCloseToDeadline).invoke(deadline)

        // then
        assertTrue(actualTasks.size == 3)
    }

    @Test
    fun `get close to deadline tasks - date is past - should return empty list`() {
        // given
        val deadline = LocalDateTime.now().minusDays(3L)

        // when
        val actualTasks = GetCloseToDeadlineTasksScenario(findTasksCloseToDeadline).invoke(deadline)

        // then
        assertTrue(actualTasks.isEmpty())
    }

    companion object {
        private val findTasksCloseToDeadline = findTasksByDeadlineRange()
    }
}