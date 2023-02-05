package com.stringconcat.todo.cleanarchitecture.task

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class TaskDeadlineTest {
    @Test
    fun `deadline in the past - must throw error`() {
        assertThrows<IllegalArgumentException> {
            TaskDeadline.of(LocalDateTime.now().minusNanos(1L))
        }
    }

    @Test
    fun `deadline in the future - doesn't throw error`() {
        assertDoesNotThrow {
            TaskDeadline.of(LocalDateTime.now().plusDays(1L))
        }
    }
}
