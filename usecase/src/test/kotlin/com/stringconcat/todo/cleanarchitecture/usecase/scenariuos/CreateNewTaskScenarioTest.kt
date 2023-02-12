package com.stringconcat.todo.cleanarchitecture.usecase.scenariuos

import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskDescription
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskFactory
import com.stringconcat.todo.cleanarchitecture.usecase.access.testfixtures.persistTask
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CreateNewTaskScenarioTest {
    @Test
    fun `create new task - valid input data - should create task`() {
        // given
        val taskFactory = taskFactory()
        val description = taskDescription()
        val deadline = taskDeadline()
        val persistTask = persistTask()

        // when
        val actualTask = CreateNewTaskScenario(taskFactory, persistTask).invoke(description, deadline)

        // then
        assertEquals(description, actualTask.description)
        assertEquals(deadline, actualTask.deadline)
    }
}