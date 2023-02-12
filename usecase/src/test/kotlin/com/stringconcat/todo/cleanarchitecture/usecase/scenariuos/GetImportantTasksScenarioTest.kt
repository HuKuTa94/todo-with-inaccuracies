package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.usecase.access.testfixtures.findTasksByPriority
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GetImportantTasksScenarioTest {
    @Test
    fun `get important tasks - should return list of high priority tasks`() {
        // when
        val actualTasks = GetImportantTasksScenario(findTasksByPriority()).invoke()

        // then
        assertTrue(actualTasks.isNotEmpty())
        actualTasks.forEach{ task ->
            assertEquals(Task.Priority.HIGH, task.priority)
        }
    }
}