package com.nicnicdev.suacasasemprelimpa03.data

import com.nicnicdev.suacasasemprelimpa03.domain.model.UserRegistration

class RegistrationRepository {
    fun register(user: UserRegistration): Result<Unit> {
        //Lógica para salvar o usuario no banco de dados
        return Result.success(Unit)
    }
}