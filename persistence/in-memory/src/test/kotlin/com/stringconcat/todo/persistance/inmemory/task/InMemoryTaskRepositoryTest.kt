package com.stringconcat.todo.persistance.inmemory.task

import com.stringconcat.todo.cleanarchitecture.domain.task.Task
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.task
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskDescription
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.taskId
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.maps.shouldHaveKey
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class InMemoryTaskRepositoryTest {

    private val inMemoryTaskRepository = InMemoryTaskRepository()

    @BeforeEach
    fun beforeAll() {
        inMemoryTaskRepository.storage[taskId()] = task(
            taskDescription("Task #1"),
            taskDeadline(LOCAL_DATE_TIME.plusDays(1)),
            Task.Priority.HIGH
        )
        inMemoryTaskRepository.storage[taskId()] = task(
            taskDescription("Task #2"),
            taskDeadline(LOCAL_DATE_TIME.plusDays(2)),
            Task.Priority.MIDDLE
        )
        inMemoryTaskRepository.storage[taskId()] = task(
            taskDescription("Task #3"),
            taskDeadline(LOCAL_DATE_TIME.plusDays(3)),
            Task.Priority.MIDDLE
        )
        inMemoryTaskRepository.storage[taskId()] = task(
            taskDescription("Task #4"),
            taskDeadline(LOCAL_DATE_TIME.plusDays(3)),
            Task.Priority.MIDDLE
        )
        inMemoryTaskRepository.storage[taskId()] = task(
            taskDescription("Task #5"),
            taskDeadline(LOCAL_DATE_TIME.plusDays(5)),
            Task.Priority.LOW
        )
        inMemoryTaskRepository.storage[taskId()] = task(
            taskDescription("Task #6"),
            taskDeadline(LOCAL_DATE_TIME.plusDays(6)),
            Task.Priority.LOW
        )
        inMemoryTaskRepository.storage[taskId()] = task(
            taskDescription("Task #7"),
            taskDeadline(LOCAL_DATE_TIME.plusDays(7)),
            Task.Priority.NEED_CALCULATION
        )
    }

    @Test
    fun `persist task - should add task to storage`() {
        // given
        val taskBeforePersist = task()
        val tasksCountBeforePersist = inMemoryTaskRepository.storage.size

        // when
        inMemoryTaskRepository.persist(taskBeforePersist)

        // then
        inMemoryTaskRepository.storage shouldHaveKey taskBeforePersist.id
        inMemoryTaskRepository.storage shouldHaveSize tasksCountBeforePersist + 1
        inMemoryTaskRepository.storage[taskBeforePersist.id] shouldBeSameInstanceAs taskBeforePersist
    }

    @Test
    fun `find tasks by low priority`() {
        inMemoryTaskRepository.find(Task.Priority.LOW) shouldHaveSize 2
    }

    @Test
    fun `find tasks by middle priority`() {
        inMemoryTaskRepository.find(Task.Priority.MIDDLE) shouldHaveSize 3
    }

    @Test
    fun `find tasks by high priority`() {
        inMemoryTaskRepository.find(Task.Priority.HIGH) shouldHaveSize 1
    }

    @Test
    fun `find tasks without priority`() {
        inMemoryTaskRepository.find(Task.Priority.NEED_CALCULATION) shouldHaveSize 1
    }

    @Test
    fun `find tasks by deadline range - large range - list of all test tasks`() {
        inMemoryTaskRepository.find(
            LOCAL_DATE_TIME,
            LOCAL_DATE_TIME.plusYears(1)
        ) shouldHaveSize 7
    }

    @Test
    fun `find tasks by deadline range - small range - list of some test tasks`() {
        inMemoryTaskRepository.find(
            LOCAL_DATE_TIME.plusDays(2),
            LOCAL_DATE_TIME.plusDays(4)
        ) shouldHaveSize 3
    }

    @Test
    fun `find tasks by deadline range - out of bound - empty list`() {
        // far in the past
        inMemoryTaskRepository.find(
            LOCAL_DATE_TIME.minusYears(2),
            LOCAL_DATE_TIME.minusYears(1),
        ) shouldHaveSize 0

        // far in the future
        inMemoryTaskRepository.find(
            LOCAL_DATE_TIME.plusYears(1),
            LOCAL_DATE_TIME.plusYears(2),
        ) shouldHaveSize 0
    }

    companion object {
        private val LOCAL_DATE_TIME = LocalDateTime.of(
            LocalDate.now(),
            LocalTime.of(LocalTime.now().hour, 0, 0) // exclude minutes and seconds from comparing
        )
    }
}