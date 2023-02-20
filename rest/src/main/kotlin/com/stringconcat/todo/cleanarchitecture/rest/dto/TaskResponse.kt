
package com.stringconcat.todo.cleanarchitecture.rest.dto

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import java.time.LocalDateTime
import java.util.*

data class TaskResponse(
    val id: UUID,
    val description: String,
    val deadline: LocalDateTime,
    val priority: String
) {

    companion object {
        fun of(task: Task): TaskResponse =
            TaskResponse(
                id = task.id.toUUID(),
                description = task.description.toString(),
                deadline = task.deadline.toLocalDateTime(),
                priority = task.priority.toString()
            )
    }
}