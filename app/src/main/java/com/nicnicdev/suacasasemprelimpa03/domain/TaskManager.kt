package com.nicnicdev.suacasasemprelimpa03.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TaskManager<T> {
    var selectedItems by mutableStateOf<List<T>>(emptyList()) // Estado genérico para itens selecionados (datas ou semanas)
        private set
    var taskMap by mutableStateOf(mutableMapOf<T,List<String>>()) // Mapa para armazenar tarefas associadas a cada item
        private set

    var newTaskMap by mutableStateOf(mutableMapOf<T, String>()) // Mapa para armazenar novas tarefas enquanto estão sendo digitadas
        private set

    fun addItem(item: T) { // Adicionar um item à lista de selecionados
        if (!selectedItems.contains(item)){
            selectedItems = selectedItems + item
        }
    }
    fun removeItem(item:T) { // Remover um item da lista de selecionados
        selectedItems = selectedItems - item
    }
    fun updateNewTask(item: T, task: String) {  // Atualizar o texto da nova tarefa
        newTaskMap = newTaskMap.toMutableMap().apply {
            this[item] = task
        }
    }

    fun assTask(item: T) { // Adicionar a nova tarefa ao mapa de tarefas
        val task = newTaskMap[item]?.trim()
        if (!task.isNullOrEmpty()){
            taskMap = taskMap.toMutableMap().apply {
                val currentTasks = this[item] ?: emptyList()
                this[item] = currentTasks + task
            }
            newTaskMap = newTaskMap.toMutableMap().apply {
                this[item] = ""
            }
        }
    }
    fun getTasks(item: T): List<String> { // Obter tarefas associadas a um item
        return taskMap[item] ?: emptyList()
    }
}