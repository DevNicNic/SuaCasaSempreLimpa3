package com.nicnicdev.suacasasemprelimpa03.ui.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import com.nicnicdev.suacasasemprelimpa03.domain.TaskManager
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleWeeklyTasksScreen(userName: String, onBackClick: () -> Unit) {
    val taskManager =
        remember { TaskManager<LocalDate>() }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Olá, $userName! Bem-vindo ao Agendar Tarefas Semanais! " +
                            "Organize suas atividades semanais de forma prática e eficiente.",
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            }
            // Botão para selecionar uma data
            item {
                DatePickerButton(onDateSelected = { selectedDate ->
                    taskManager.addItem(selectedDate)
                })
            }
            // Exibir semanas selecionadas
            val selectedDates = taskManager.selectedItems
            if (selectedDates.isNotEmpty()) {
                item {
                    Text(
                        text = "Datas Selecionadas: ${selectedDates.joinToString(", "){ "${it.dayOfMonth}/${it.monthValue}/${it.year}" }}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            // Exibir tarefas para cada data
            selectedDates.forEach { date ->
                item {
                    Text(
                        text = "Tarefas para ${date.dayOfMonth}/${date.monthValue}/${date.year}" ,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        val newTask = taskManager.getNewTask(date)
                        TextField(
                            value = newTask ?: "",
                            onValueChange = { newValue ->
                                taskManager.updateNewTask(date, newValue)
                            },
                            placeholder = { Text(text = "Digite uma nova tarefa") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        )
                        Button(
                            onClick = {
                                taskManager.addTask(date)
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = "Adicionar Tarefa")
                        }
                        taskManager.getTasks(date).forEach { task ->
                            Text(
                                text = "- $task",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
            item {
                val coroutineScope = rememberCoroutineScope()

                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Tarefas salvas com sucesso!")
                        }
                    },
                    enabled = selectedDates.isNotEmpty(),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Salvar Tarefas")
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ScheduleWeeklyTasksScreenPreview() {
    ScheduleWeeklyTasksScreen(userName = "Nicole", onBackClick = {})
}




