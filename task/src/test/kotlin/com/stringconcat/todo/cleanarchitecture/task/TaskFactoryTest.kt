package com.stringconcat.todo.cleanarchitecture.task

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.time.LocalDateTime

class TaskFactoryTest {
    @Test
    fun `correct input data - must create task and change priority`() {
        // given
        val priorityProvider: PriorityProvider = mock()
        val taskFactory = TaskFactory(priorityProvider)

        val expectedTaskDescription = requireNotNull(TaskDescription.of("task description").getOrNull())
        val expectedTaskDeadline = requireNotNull(TaskDeadline.of(LocalDateTime.now().plusDays(1L)).getOrNull())
        val expectedPriority = Task.Priority.MIDDLE

        // when
        whenever(priorityProvider.calculatePriority(any())).thenReturn(expectedPriority)

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
