package com.stringconcat.todo.cleanarchitecture.rest.dto

data class TaskRequest(
    val description: String,
    val deadline: Long,
)