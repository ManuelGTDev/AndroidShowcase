package com.mgtdev.androidshowcase.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mgtdev.androidshowcase.auth.LoginScreen
import com.mgtdev.androidshowcase.auth.LoginUiState
import com.mgtdev.androidshowcase.auth.LoginViewModel

@Composable
fun LoginNavigationWrapper() {
    val loginViewModel: LoginViewModel = hiltViewModel()
    val formState by loginViewModel.formState.collectAsStateWithLifecycle()
    val loginState by loginViewModel.uiState.collectAsStateWithLifecycle()

    val activity = LocalActivity.current
    val backStack = rememberNavBackStack(LoginKey)

    LaunchedEffect(loginState) {
        val destination = if (loginState is LoginUiState.Success) {
            PrincipalWrapperKey
        } else {
            LoginKey
        }

        if (backStack.lastOrNull() != destination) {
            backStack.clearAndNavigate(destination)
        }
    }

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        onBack = {
            activity?.finish()
        },
        entryProvider = entryProvider {
            entry<LoginKey> {
                LoginScreen(
                    formState = formState,
                    uiState = loginState,
                    onUserNameChanged = loginViewModel::onUserNameChanged,
                    onPasswordChanged = loginViewModel::onPasswordChanged,
                    onLoginClick = loginViewModel::login,
                )
            }

            entry<PrincipalWrapperKey> {
                PrincipalNavigationWrapperNav3(
                    onLogout = loginViewModel::logout,
                )
            }
        },
    )
}
