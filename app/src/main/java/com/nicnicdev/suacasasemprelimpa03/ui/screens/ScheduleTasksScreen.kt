package com.nicnicdev.suacasasemprelimpa03.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import android.app.DatePickerDialog
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicnicdev.suacasasemprelimpa03.domain.TaskManager
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Year
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleTasksScreen(userName: String, onBackClick: () -> Unit) {
    val taskManager = remember { TaskManager<LocalDate>() }

    val snackbarHostState = remember { SnackbarHostState() }
    val dateDialogState = rememberMaterialDialogState()


    // layout principal
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
        snackbarHost = { SnackbarHost(snackbarHostState) } // adiciona o snackbar
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
                    text = "Olá, $userName ! Bem vindo ao Agendar Tarefas Diárias! " +
                            "Aqui você pode organizar suas atividade de forma prática " +
                            "e eficiente.",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 16.sp
                    ),
                    textAlign = TextAlign.Center
                )
            }
            item {   //botão para abrir o calendario
                DatePickerButton(onDateSelected = { selectedDate ->
                    taskManager.addItem(selectedDate)
                })
            }

            // exibir as datas selecionadas
            val selectedDates = taskManager.selectedItems
            if (selectedDates.isNotEmpty()) {
                item {
                    Text(
                        text = "Datas Selecionadas: ${
                            selectedDates.joinToString(",")
                            { "${it.dayOfMonth}/${it.monthValue}/${it.year}" }
                        }",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            selectedDates.forEach() { date ->
                item {
                    Text(
                        text = "Tarefas para ${date.dayOfMonth}/${date.monthValue}/${date.year}",
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
                                text = "-$task",
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
                    enabled = selectedDates.isNotEmpty(),  // habilitado apenas se houver datas selecionadas
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Salvar Tarefas ")
                }
            }
        }
        MaterialDialog(dialogState = dateDialogState, buttons = {
            positiveButton("Confirmar")
            negativeButton("Cancelar")
        }) {
            // Lista de todas as datas do ano atual
            val currentYear = Year.now().value
            val datesInYear = List(Year.of(currentYear).length()) { day ->
                LocalDate.ofYearDay(currentYear, day + 1)
            }
            LazyColumn {
                items(datesInYear) { date ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                if (taskManager
                                        .selectedItems
                                        .contains(date)
                                ) {
                                    taskManager.removeItem(date)
                                } else {
                                    taskManager.addItem(date)
                                }
                            }
                    ) {
                        Checkbox(
                            checked = taskManager.selectedItems.contains(date),
                            onCheckedChange = {
                                if (it) {
                                    taskManager.addItem(date)
                                } else {
                                    taskManager.removeItem(date)
                                }
                            }
                        )
                        Text(text = "${date.dayOfMonth}/${date.monthValue}/${date.year}")
                    }
                }
            }
        }
    }
}

@Composable
fun DatePickerButton(onDateSelected: (LocalDate) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Button(
        onClick = {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    onDateSelected(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        },
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(text = "Selecionar Data")
    }
}


@Preview(showBackground = true)
@Composable
fun ScheduleTasksScreenPreview() {
    ScheduleTasksScreen(userName = "Nicole", onBackClick = {})
}