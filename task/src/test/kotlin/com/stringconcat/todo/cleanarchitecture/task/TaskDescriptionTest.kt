package com.stringconcat.todo.cleanarchitecture.task

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TaskDescriptionTest {
    @Test
    fun `empty task description - must throw error`() {
        assertThrows<IllegalArgumentException> { TaskDescription.of("") }
        assertThrows<IllegalArgumentException> { TaskDescription.of(" ") }
    }

    @Test
    fun `correct task description - doesn't throw error`() {
        assertDoesNotThrow { TaskDescription.of("Some task description") }
    }

    @Test
    fun `task description with extra spaces - must trim description`() {
        val descriptionWithSpacesAtStart = "   Some task description with spaces at start"
        val descriptionWithSpacesAtEnd = "Some task description with spaces at end        "

        assertEquals(descriptionWithSpacesAtStart.trim(), TaskDescription.of(descriptionWithSpacesAtStart).toString())
        assertEquals(descriptionWithSpacesAtEnd.trim(), TaskDescription.of(descriptionWithSpacesAtEnd).toString())
    }
}
