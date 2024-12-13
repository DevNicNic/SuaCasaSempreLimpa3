package com.nicnicdev.suacasasemprelimpa03.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicnicdev.suacasasemprelimpa03.data.RegistrationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val registrationRepository: RegistrationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onLogin(email: String, password: String) {
        _uiState.value = LoginUiState()

        viewModelScope.launch {
            val result = registrationRepository.login(email, password)
            if (result.isSuccess) {
                _uiState.value = LoginUiState(success = true)
            } else {
                _uiState.value = LoginUiState(error = result.exceptionOrNull()?.message)
            }
        }
    }
}

data class LoginUiState(
    val success: Boolean = false,
    val error: String? = null
)