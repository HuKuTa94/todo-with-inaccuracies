package com.stringconcat.todo.persistance.inmemory.task

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskId
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksByDeadlineRange
import com.stringconcat.todo.cleanarchitecture.usecase.access.FindTasksByPriority
import com.stringconcat.todo.cleanarchitecture.usecase.access.PersistTask
import java.time.LocalDateTime

class InMemoryTaskRepository : PersistTask, FindTasksByPriority, FindTasksByDeadlineRange {

    internal val storage = HashMap<TaskId, Task>()

    override fun persist(task: Task) {
        storage[task.id] = task
    }

    override fun find(priority: Task.Priority): List<Task> =
        storage.values.filter { it.priority == priority }.toList()

    override fun find(from: LocalDateTime, to: LocalDateTime): List<Task> =
        storage.values.filter {
            val deadline = it.deadline.toLocalDateTime()
            (deadline.isEqual(from) || deadline.isAfter(from)) && deadline.isBefore(to)
        }.toList()
}