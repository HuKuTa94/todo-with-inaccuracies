package com.stringconcat.todo.cleanarchitecture.task

data class TaskDescription internal constructor(
    private var value: String
){
    init {
        require(value.isNotBlank()) { "Invalid task description" }
        value = value.trim()
    }

    override fun toString(): String = value

    companion object {
        fun from(description: String) = TaskDescription(description)
    }
}