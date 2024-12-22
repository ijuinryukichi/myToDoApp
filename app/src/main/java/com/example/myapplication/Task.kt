package com.example.myapplication

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    var isCompleted: Boolean
)