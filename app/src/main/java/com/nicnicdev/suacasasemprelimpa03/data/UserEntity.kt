package com.nicnicdev.suacasasemprelimpa03.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users") // tabela do banco de dados
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, //ID chave primaria gerada automaticamente
    val name : String,
    val email : String,
    val password : String,
)
