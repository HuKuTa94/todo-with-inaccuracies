package com.stringconcat.todo.cleanarchitecture.neuralnetwork

import com.stringconcat.todo.cleanarchitecture.domain.task.PriorityProvider
import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import java.time.ZoneOffset

class NeuralNetworkTaskPriorityProvider : PriorityProvider {

    override fun calculatePriority(task: Task): Task.Priority {
        val neuralNetworkValue = mod(
            sum(
                task.description.toString().length.toLong(),
                task.deadline.toLocalDateTime().toEpochSecond(ZoneOffset.UTC)
            ),
            TASK_PRIORITY_COUNT_OF_VALUES
        )
        return neuralNetworkValueToPriority(neuralNetworkValue)
    }

    internal fun sum(number1: Long, number2: Long) = number1 + number2

    internal fun mod(dividend: Long, divisor: Long) = dividend % divisor

    internal fun neuralNetworkValueToPriority(neuralNetworkValue: Long) =
        when (neuralNetworkValue) {
            0L -> Task.Priority.LOW
            1L -> Task.Priority.MIDDLE
            2L -> Task.Priority.HIGH
            else -> throw IllegalStateException("Invalid rating param value")
        }

    companion object {
        private val TASK_PRIORITY_COUNT_OF_VALUES = Task.Priority.values().size - 1L // exclude NEED_CALCULATION from count of values
    }
}