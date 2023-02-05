package com.stringconcat.todo.cleanarchitecture.task

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.time.LocalDateTime
import java.time.ZoneOffset

@JvmInline
value class TaskDeadline private constructor(
    private val value: LocalDateTime
){
    fun toLocalDateTime(): LocalDateTime = value

    companion object {
        fun of(deadline: Long) = of(LocalDateTime.ofEpochSecond(deadline, 0, ZoneOffset.UTC))

        fun of(deadline: LocalDateTime): Either<CreateTaskDeadlineError, TaskDeadline> =
            when {
                deadline.isBefore(LocalDateTime.now()) -> CreateTaskDeadlineError.OverdueDate.left()
                else -> TaskDeadline(deadline).right()
            }
    }
}

sealed class CreateTaskDeadlineError : Throwable() {
    object OverdueDate : CreateTaskDeadlineError()
}