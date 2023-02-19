package com.stringconcat.todo.cleanarchitecture.neuralnetwork

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDescription
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.task
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskDescription
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.ZoneOffset
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

    @Test
    fun `neuralNetworkValueToPriority - unsupported negative value - should throw exception`() {
        executePropertyBasedTest {
            val number = (Long.MIN_VALUE..-1).random()
            assertThrows<IllegalStateException> {
                neuralNetworkTaskPriorityProvider.neuralNetworkValueToPriority(number)
            }
        }
    }

    @Test
    fun `neuralNetworkValueToPriority - unsupported positive value - should throw exception`() {
        executePropertyBasedTest {
            val number = (3..Long.MAX_VALUE).random()
            assertThrows<IllegalStateException> {
                neuralNetworkTaskPriorityProvider.neuralNetworkValueToPriority(number)
            }
        }
    }

    @Test
    fun `neuralNetworkValueToPriority - value 0 - should be low priority`() {
        neuralNetworkTaskPriorityProvider.neuralNetworkValueToPriority(0) shouldBe Task.Priority.LOW
    }

    @Test
    fun `neuralNetworkValueToPriority - value 1 - should be middle priority`() {
        neuralNetworkTaskPriorityProvider.neuralNetworkValueToPriority(1) shouldBe Task.Priority.MIDDLE
    }

    @Test
    fun `neuralNetworkValueToPriority - value 2 - should be high priority`() {
        neuralNetworkTaskPriorityProvider.neuralNetworkValueToPriority(2) shouldBe Task.Priority.HIGH
    }

    @Test
    fun `calculatePriority - property test - should calculate priority`() {
        executePropertyBasedTest {
            val descriptionText = "a".repeat((0..10000).random())
            val deadLineEpochSecond =
                (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)..LocalDateTime.MAX.toEpochSecond(ZoneOffset.UTC)).random()

            executeExampleBasedTest(
                taskDescription(descriptionText),
                taskDeadline(deadLineEpochSecond)
            )
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

    /**
     * Executes once Example-Based Test by open-box principle with specified params
     * @param taskDescription description of domain object [Task]
     * @param taskDeadline deadline of domain object [Task]
     */
    private fun executeExampleBasedTest(
        taskDescription: TaskDescription,
        taskDeadline: TaskDeadline
    ) {
        // given
        val task = task(taskDescription, taskDeadline)
        val sum = neuralNetworkTaskPriorityProvider.sum(
            task.description.toString().length.toLong(),
            task.deadline.toLocalDateTime().toEpochSecond(ZoneOffset.UTC)
        )
        val mod = neuralNetworkTaskPriorityProvider.mod(sum, TASK_PRIORITY_COUNT_OF_VALUES)
        val expectedPriority = neuralNetworkTaskPriorityProvider.neuralNetworkValueToPriority(mod)

        // when
        val actualPriority = neuralNetworkTaskPriorityProvider.calculatePriority(task)

        // then
        assertEquals(expectedPriority, actualPriority)
    }

    companion object {
        private const val PROPERTY_BASED_TEST_REPEAT_COUNT = 1000L
        private val TASK_PRIORITY_COUNT_OF_VALUES = Task.Priority.values().size - 1L // exclude NEED_CALCULATION from count of values
    }
}