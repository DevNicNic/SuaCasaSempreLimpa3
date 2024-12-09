package com.nicnicdev.suacasasemprelimpa03.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicnicdev.suacasasemprelimpa03.data.RegistrationRepository
import com.nicnicdev.suacasasemprelimpa03.domain.model.UserRegistration
import com.nicnicdev.suacasasemprelimpa03.domain.useCase.ValidadeRegistrationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class RegisterViewModel (
    private val validadeRegistrationUseCase: ValidadeRegistrationUseCase,
    private val registrationRepository: RegistrationRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onRegister (name: String, email: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            val validation = validadeRegistrationUseCase.execute(name, email, password, confirmPassword)
            if (validation.isSuccess) {
                val result =
                    registrationRepository.register(UserRegistration(name, email, password))
                if (result.isSuccess) {
                    _uiState.value = RegisterUiState(success = true)
                } else {
                    _uiState.value = RegisterUiState(error = result.exceptionOrNull()?.message)
                }
            }else {
                _uiState.value = RegisterUiState(error = validation.exceptionOrNull()?.message)

            }
        }
    }
}

data class RegisterUiState(
    val success: Boolean = false,
    val error: String? = null
)