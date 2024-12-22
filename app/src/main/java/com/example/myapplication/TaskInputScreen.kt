package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInputScreen(
    taskId: Int,
    onSave: () -> Unit
) {

    val taskInputViewModel: TaskInputViewModel = hiltViewModel()

    LaunchedEffect(taskId) {
        if (taskId == 0) {
            taskInputViewModel.taskTitle.value = ""
            taskInputViewModel.taskDescription.value = ""
        } else {
            taskInputViewModel.loadTask(taskId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Input Screen") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = taskInputViewModel.taskTitle.value,
                onValueChange = { taskInputViewModel.taskTitle.value = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = taskInputViewModel.taskDescription.value,
                onValueChange = { taskInputViewModel.taskDescription.value = it },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    taskInputViewModel.saveTask()
                    onSave()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Task")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskInputScreenPreview() {
    TaskInputScreen(taskId = 0, onSave = {})
}