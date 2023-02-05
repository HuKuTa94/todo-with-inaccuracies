package com.stringconcat.todo.cleanarchitecture.task

import java.time.LocalDateTime
import java.time.ZoneOffset

@JvmInline
value class TaskDeadline private constructor(
    private val value: LocalDateTime
){
    init {
        require(value.isAfter(LocalDateTime.now())) { "Deadline can't be in the past" }
    }

    fun toLocalDateTime(): LocalDateTime = value

    companion object {
        fun of(deadline: LocalDateTime) = TaskDeadline(deadline)

        fun of(deadline: Long) = TaskDeadline(
            LocalDateTime.ofEpochSecond(
                deadline, 0, ZoneOffset.UTC
            )
        )
    }
}