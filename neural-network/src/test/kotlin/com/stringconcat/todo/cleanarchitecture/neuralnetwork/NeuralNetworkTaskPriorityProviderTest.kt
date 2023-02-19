package com.stringconcat.todo.cleanarchitecture.neuralnetwork

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import io.kotest.matchers.longs.shouldBeExactly
import org.junit.jupiter.api.Test
import java.util.*

class NeuralNetworkTaskPriorityProviderTest {
    private val neuralNetworkTaskPriorityProvider = NeuralNetworkTaskPriorityProvider()
    private val random = Random()

    @Test
    fun `sum - property test - negative and positive numbers`() {
        executePropertyBasedTest {
            val number1 = random.nextLong()
            val number2 = random.nextLong()

            val left = neuralNetworkTaskPriorityProvider.sum(number1, number2)
            val right = neuralNetworkTaskPriorityProvider.sum(number2, number1)

            left shouldBeExactly right
        }
    }

    @Test
    fun `mod - property test - only positive numbers`() {
        executePropertyBasedTest {
            val dividend = (0..Long.MAX_VALUE).random()
            val divisor = TASK_PRIORITY_COUNT_OF_VALUES
            val remainder = (0..TASK_PRIORITY_COUNT_OF_VALUES).random()

            val left = neuralNetworkTaskPriorityProvider.mod(dividend + remainder, divisor)
            val right = neuralNetworkTaskPriorityProvider.mod(
                neuralNetworkTaskPriorityProvider.mod(dividend, divisor) + neuralNetworkTaskPriorityProvider.mod(remainder, divisor),
                divisor
            )

            left shouldBeExactly right
        }
    }

    /**
     * Executes Property-Based Test that should be repeated a lot of times
     * @param function lambda-expression of test
     */
    private fun executePropertyBasedTest(function: () -> Unit) {
        for (i in 0..PROPERTY_BASED_TEST_REPEAT_COUNT) {
            function()
        }
    }

    companion object {
        private const val PROPERTY_BASED_TEST_REPEAT_COUNT = 1000L
        private val TASK_PRIORITY_COUNT_OF_VALUES = Task.Priority.values().size - 1L // exclude NEED_CALCULATION from count of values
    }
}