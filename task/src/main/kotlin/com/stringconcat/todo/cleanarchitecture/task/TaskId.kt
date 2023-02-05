package com.stringconcat.todo.cleanarchitecture.task

import java.util.UUID

@JvmInline
value class TaskId internal constructor(
    private val value: UUID? = UUID.randomUUID()
){
    fun toUUID() = value

    companion object {
        fun of(id: UUID) = TaskId(id)
    }
}