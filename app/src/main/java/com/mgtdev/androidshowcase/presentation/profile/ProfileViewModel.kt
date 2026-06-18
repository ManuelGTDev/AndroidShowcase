package com.mgtdev.androidshowcase.presentation.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Idle)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        _uiState.value = ProfileUiState.Loading
        _uiState.value = ProfileUiState.Success(title = "Perfil")
    }
}

sealed interface ProfileUiState {
    data object Idle : ProfileUiState
    data object Loading : ProfileUiState

    data class Success(
        val title: String,
    ) : ProfileUiState

    data class Error(
        val message: String,
    ) : ProfileUiState
}