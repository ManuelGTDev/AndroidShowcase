package com.mgtdev.androidshowcase.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgtdev.domain.Resource
import com.mgtdev.domain.UiText
import com.mgtdev.domain.model.Product
import com.mgtdev.domain.usecase.GetSomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSomeDataUseCase: GetSomeDataUseCase,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<DetailUiState>(DetailUiState.Idle)

    val uiState: StateFlow<DetailUiState> =
        _uiState.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = DetailUiState.Loading

            _uiState.value = when (val result = getSomeDataUseCase()) {
                is Resource.Success -> {
                    DetailUiState.Success(
                        products = result.data.orEmpty(),
                    )
                }

                is Resource.Error -> {
                    DetailUiState.Error(
                        message = result.message.toMessage(),
                    )
                }

                is Resource.Loading -> {
                    DetailUiState.Loading
                }
            }
        }
    }
}

sealed interface DetailUiState {

    data object Idle : DetailUiState

    data object Loading : DetailUiState

    data class Success(
        val products: List<Product>,
    ) : DetailUiState

    data class Error(
        val message: String,
    ) : DetailUiState
}

private fun UiText?.toMessage(): String =
    when (this) {
        is UiText.DynamicString -> value
        is UiText.StringResource -> "Se ha producido un error"
        null -> "Error, prueba de nuevo más tarde"
    }