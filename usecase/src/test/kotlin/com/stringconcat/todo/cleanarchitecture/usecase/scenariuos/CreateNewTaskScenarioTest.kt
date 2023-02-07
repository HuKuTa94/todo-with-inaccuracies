package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.task.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateNewTaskScenarioTest {
    @Test
    fun `create new task - valid input data - should create task`() {
        // given
        val taskFactory = taskFactory()
        val description = taskDescription()
        val deadline = taskDeadline()

        // when
        val actualTask = CreateNewTaskScenario(taskFactory).invoke(description, deadline)

        // then
        assertEquals(description, actualTask.description)
        assertEquals(deadline, actualTask.deadline)
    }
}