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
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate


@Composable
fun ScheduleTasksScreen(userName: String) {
    // Estado para guardar a lista de dados selecionadas
    var selectedDates by remember { mutableStateOf<List<LocalDate>>(emptyList()) }
    //estado do dialogo de seleção de data
    val dateDialogState = rememberMaterialDialogState()


    // layout principal
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Olá, $userName ! Bem vindo ao Agendar Tarefas Diárias! " +
                        "Aqui você pode organizar suas atividade de forma prática " +
                        "e eficiente.",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Center
            )
            //botão para abrir o calendario
            Button(
                onClick = { dateDialogState.show() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Selecionar Data")
            }
            // exibir as datas selecionadas
            if (selectedDates.isNotEmpty()) {
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
    }
    MaterialDialog(dialogState = dateDialogState, buttons = {
        positiveButton("Confirmar")
        negativeButton("Cancelar")
    }) {
        // Lista de dias do mês atual para seleção
        val today = LocalDate.now()
        val daysInMonth = today.lengthOfMonth()
        val datesInMonth = List(daysInMonth) { today.withDayOfMonth(it + 1) }

        LazyColumn {
            items(datesInMonth) { date ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            if (selectedDates.contains(date)) {
                                selectedDates = selectedDates - date
                            } else {
                                selectedDates = selectedDates + date
                            }
                        }
                ) {
                    Checkbox(
                        checked = selectedDates.contains(date),
                        onCheckedChange = {
                            if (it) {
                                selectedDates = selectedDates + date
                            } else {
                                selectedDates = selectedDates - date
                            }
                        }
                    )
                    Text(text = "${date.dayOfMonth}/${date.monthValue}/${date.year}")
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ScheduleTasksScreenPreview() {
    ScheduleTasksScreen(userName = "Nicole")
}