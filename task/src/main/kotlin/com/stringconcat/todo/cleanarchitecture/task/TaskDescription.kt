package com.stringconcat.todo.cleanarchitecture.task

@JvmInline
value class TaskDescription private constructor(
    private val value: String
){
    override fun toString(): String = value

    companion object {
        fun of(description: String): TaskDescription {
            require(description.isNotBlank()) { "Invalid task description" }
            return TaskDescription(description.trim())
        }
    }
}