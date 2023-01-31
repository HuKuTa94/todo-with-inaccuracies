package com.stringconcat.todo.cleanarchitecture.task

import java.util.UUID

data class TaskId internal constructor(
    private val value: UUID? = UUID.randomUUID()
){
    fun toUUID() = value

    companion object {
        fun from(id: UUID) = TaskId(id)
    }
}