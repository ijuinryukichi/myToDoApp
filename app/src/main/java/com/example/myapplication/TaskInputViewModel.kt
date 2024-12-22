package com.example.myapplication

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskInputViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {
    var taskTitle = mutableStateOf("")
    var taskDescription = mutableStateOf("")

    fun saveTask() {
        val taskId = generateTaskId()
        val task = Task(
            id = taskId,
            title = taskTitle.value,
            description = taskDescription.value,
            isCompleted = false
        )
        saveTaskToPreferences(task)
    }

    fun loadTask(taskId: Int) {
        val taskSet = sharedPreferences.getStringSet("tasks", emptySet()) ?: emptySet()
        for (taskString in taskSet) {
            val taskParts = taskString.split("|")
            if (taskParts.size == 4 && taskParts[0].toInt() == taskId) {
                taskTitle.value = taskParts[1]
                taskDescription.value = taskParts[2]
                break
            }
        }
    }

    private fun generateTaskId(): Int {
        val taskSet = sharedPreferences.getStringSet("tasks", emptySet()) ?: emptySet()
        return taskSet.size + 1
    }

    private fun saveTaskToPreferences(task: Task) {
        val taskSet = sharedPreferences.getStringSet("tasks", emptySet())?.toMutableSet() ?: mutableSetOf()
        taskSet.add("${task.id}|${task.title}|${task.description}|${task.isCompleted}")
        sharedPreferences.edit().putStringSet("tasks", taskSet).apply()
    }
}