package com.stringconcat.todo.cleanarchitecture.neuralnetwork

import com.stringconcat.todo.cleanarchitecture.domain.task.PriorityProvider
import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import java.time.ZoneOffset

class NeuralNetworkTaskPriorityProvider : PriorityProvider {
    override fun calculatePriority(task: Task): Task.Priority {
        val neuralNetworkValue =
            (task.description.toString().length +
             task.deadline.toLocalDateTime().toEpochSecond(ZoneOffset.UTC)) % 3

        return when (neuralNetworkValue) {
            0L -> Task.Priority.LOW
            1L -> Task.Priority.MIDDLE
            2L -> Task.Priority.HIGH
            else -> throw IllegalStateException("Invalid rating param value")
        }
    }
}