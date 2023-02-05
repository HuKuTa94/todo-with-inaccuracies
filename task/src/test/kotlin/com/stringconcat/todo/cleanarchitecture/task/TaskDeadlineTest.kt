package com.stringconcat.todo.cleanarchitecture.task

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskDeadlineTest {
    @Test
    fun `deadline in the past - must throw error`() {
        TaskDeadline.of(
            LocalDateTime.now().minusNanos(1L)
        ).shouldBeLeft()
    }

    @Test
    fun `deadline in the future - doesn't throw error`() {
        TaskDeadline.of(
            LocalDateTime.now().plusDays(1L)
        ).shouldBeRight()
    }
}
