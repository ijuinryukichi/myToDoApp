package com.example.myapplication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.MainViewModel
import com.example.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        mainViewModel.reloadTasks()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Task List") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("taskInput/0") }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add_task))
            }
        }
    ) { innerPadding ->
        TaskList(
            tasks = mainViewModel.tasks,
            onTaskCheckedChange = { task, isChecked ->
                mainViewModel.updateTaskStatus(task, isChecked)
            },
            onTaskClick = { task ->
                navController.navigate("taskInput/${task.id}")
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}