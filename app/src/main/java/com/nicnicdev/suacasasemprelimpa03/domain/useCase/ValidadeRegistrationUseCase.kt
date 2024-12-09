package com.nicnicdev.suacasasemprelimpa03.domain.useCase

class ValidadeRegistrationUseCase {
    fun execute(name: String, email: String,password: String, confirmPassword: String) : Result<Unit>{
        return when {
            name.isBlank() -> Result.failure(Exception("Nome não pode estar vazio"))
            email.isBlank() -> Result.failure(Exception("E-mail não pode estar vazio"))
            password.isBlank() -> Result.failure(Exception("Senha não pode estar vazia"))
            password != confirmPassword -> Result.failure(Exception("As senhas não coincidem "))
            else -> Result.success(Unit)
        }
    }
}