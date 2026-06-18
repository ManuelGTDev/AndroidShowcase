package com.mgtdev.androidshowcase.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onLogoutClick: () -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            ProfileUiState.Idle -> {
                Text(text = "Perfil sin cargar")

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onRetry) {
                    Text(text = "Cargar")
                }
            }

            ProfileUiState.Loading -> {
                CircularProgressIndicator()
            }

            is ProfileUiState.Success -> {
                Text(
                    text = uiState.title,
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = onLogoutClick) {
                    Text(text = "Volver al login")
                }
            }

            is ProfileUiState.Error -> {
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onRetry) {
                    Text(text = "Reintentar")
                }
            }
        }
    }
}
