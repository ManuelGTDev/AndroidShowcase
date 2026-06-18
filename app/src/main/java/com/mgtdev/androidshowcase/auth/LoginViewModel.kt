package com.mgtdev.androidshowcase.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onUserNameChanged(value: String) {
        _formState.update { currentState ->
            currentState.copy(userName = value)
        }
        clearError()
    }

    fun onPasswordChanged(value: String) {
        _formState.update { currentState ->
            currentState.copy(password = value)
        }
        clearError()
    }

    fun login() {
        val currentForm = _formState.value

        if (!currentForm.canLogin) {
            _uiState.value = LoginUiState.Error(
                message = "Completa el usuario y la contraseña",
            )
            return
        }

        viewModelScope.launch(context = Dispatchers.IO) {
            _uiState.value = LoginUiState.Loading

            //Aqui va el caso de uso del login, ponemos delay para simular
            delay(2000)

            _uiState.value = LoginUiState.Success(
                userName = currentForm.userName.trim(),
            )
        }
    }

    fun logout() {
        _formState.value = LoginFormState()
        _uiState.value = LoginUiState.Idle
    }

    private fun clearError() {
        if (_uiState.value is LoginUiState.Error) {
            _uiState.value = LoginUiState.Idle
        }
    }
}

data class LoginFormState(
    val userName: String = "",
    val password: String = "",
) {
    val canLogin: Boolean
        get() = userName.isNotBlank() && password.isNotBlank()
}

sealed interface LoginUiState {
    data object Idle : LoginUiState
    data object Loading : LoginUiState

    data class Success(
        val userName: String,
    ) : LoginUiState

    data class Error(
        val message: String,
    ) : LoginUiState
}