package com.example.myapplication

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val tasks = mutableStateListOf<Task>()

    init {
        loadTasks()
    }

    fun updateTaskStatus(task: Task, isChecked: Boolean) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task.copy(isCompleted = isChecked)
            saveTasks()
        }
    }

    fun reloadTasks() {
        loadTasks()
    }

    private fun loadTasks() {
        tasks.clear()
        val taskSet = sharedPreferences.getStringSet("tasks", emptySet()) ?: emptySet()
        for (taskString in taskSet) {
            val taskParts = taskString.split("|")
            if (taskParts.size == 4) {
                val task = Task(
                    id = taskParts[0].toInt(),
                    title = taskParts[1],
                    description = taskParts[2],
                    isCompleted = taskParts[3].toBoolean()
                )
                tasks.add(task)
            }
        }
    }

    private fun saveTasks() {
        val editor = sharedPreferences.edit()
        val taskSet = tasks.map { "${it.id}|${it.title}|${it.description}|${it.isCompleted}" }.toSet()
        editor.putStringSet("tasks", taskSet)
        editor.apply()
    }
}