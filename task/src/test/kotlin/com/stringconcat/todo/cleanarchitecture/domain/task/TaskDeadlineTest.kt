package com.stringconcat.todo.cleanarchitecture.domain.task

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskDeadlineTest {
    @Test
    fun `deadline in the past - must throw error`() {
        val result = TaskDeadline.of(LocalDateTime.now().minusNanos(1L))
        result.shouldBeLeft().shouldBe(CreateTaskDeadlineError.OverdueDate)
    }

    @Test
    fun `deadline in the future - doesn't throw error`() {
        TaskDeadline.of(
            LocalDateTime.now().plusDays(1L)
        ).shouldBeRight()
    }
}
