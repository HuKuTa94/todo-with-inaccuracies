package com.stringconcat.todo.cleanarchitecture.rest.endpoint

import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDeadline
import com.stringconcat.todo.cleanarchitecture.domain.task.TaskDescription
import com.stringconcat.todo.cleanarchitecture.rest.API_V1_ADD_TASK
import com.stringconcat.todo.cleanarchitecture.rest.dto.TaskRequest
import com.stringconcat.todo.cleanarchitecture.rest.dto.TaskResponse
import com.stringconcat.todo.cleanarchitecture.usecase.CreateNewTaskUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AddTaskEndpoint(
    private val createNewTaskUseCase: CreateNewTaskUseCase
) {
    @PostMapping(path = [API_V1_ADD_TASK])
    fun execute(@RequestBody request: TaskRequest): ResponseEntity<*> {
        val errors = mutableListOf<String>()

        val taskDescription = TaskDescription.of(request.description)
            .mapLeft { errors.add(it.errorMessage) }
            .getOrNull()
        val taskDeadline = TaskDeadline.of(request.deadline).fold(
            { errors.add(it.errorMessage); null },
            { it }
        )

        return when {
            errors.isNotEmpty() -> ResponseEntity.badRequest().body(errors)
            else -> {
                val task = createNewTaskUseCase.invoke(
                    taskDescription!!,
                    taskDeadline!!
                )
                ResponseEntity.ok(TaskResponse.of(task))
            }
        }
    }
}