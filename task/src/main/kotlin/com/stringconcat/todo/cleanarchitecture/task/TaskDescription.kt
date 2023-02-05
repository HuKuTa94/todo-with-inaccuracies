package com.stringconcat.todo.cleanarchitecture.task

import arrow.core.Either
import arrow.core.left
import arrow.core.right

@JvmInline
value class TaskDescription private constructor(
    private val value: String
){
    override fun toString(): String = value

    companion object {
        fun of(description: String): Either<CreateTaskDescriptionError, TaskDescription> =
            when {
                description.isBlank() -> CreateTaskDescriptionError.EmptyString.left()
                else -> TaskDescription(description.trim()).right()
            }
    }
}

sealed class CreateTaskDescriptionError {
    object EmptyString : CreateTaskDescriptionError()
}