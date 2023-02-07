package com.stringconcat.todo.cleanarchitecture.task

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TaskFactoryTest {
    @Test
    fun `correct input data - must create task and change priority`() {
        // given
        val taskFactory = taskFactory(middlePriorityProvider())

        val expectedTaskDescription = taskDescription()
        val expectedTaskDeadline = taskDeadline()
        val expectedPriority = Task.Priority.MIDDLE

        // when
        val actualTask = taskFactory.createTask(
            expectedTaskDescription,
            expectedTaskDeadline
        )

        // then
        assertEquals(expectedTaskDescription, actualTask.description)
        assertEquals(expectedTaskDeadline, actualTask.deadline)
        assertEquals(expectedPriority, actualTask.priority)
    }
}
