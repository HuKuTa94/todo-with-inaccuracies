package com.stringconcat.todo.cleanarchitecture.task

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TaskDescriptionTest {
    @Test
    fun `empty task description - must throw error`() {
        TaskDescription.of("").shouldBeLeft().shouldBe(CreateTaskDescriptionError.EmptyString)
        TaskDescription.of(" ").shouldBeLeft().shouldBe(CreateTaskDescriptionError.EmptyString)
    }

    @Test
    fun `correct task description - doesn't throw error`() {
        TaskDescription.of("Some task description").shouldBeRight()
    }

    @Test
    fun `task description with extra spaces - must trim description`() {
        val descriptionWithSpacesAtStart = "   Some task description with spaces at start"
        val descriptionWithSpacesAtEnd = "Some task description with spaces at end        "

        val actualDescriptionWithSpacesAtStart = requireNotNull(TaskDescription.of(descriptionWithSpacesAtStart).getOrNull())
        val actualDescriptionWithSpacesAtEnd = requireNotNull(TaskDescription.of(descriptionWithSpacesAtEnd).getOrNull())

        assertEquals(descriptionWithSpacesAtStart.trim(), actualDescriptionWithSpacesAtStart.toString())
        assertEquals(descriptionWithSpacesAtEnd.trim(), actualDescriptionWithSpacesAtEnd.toString())
    }
}
