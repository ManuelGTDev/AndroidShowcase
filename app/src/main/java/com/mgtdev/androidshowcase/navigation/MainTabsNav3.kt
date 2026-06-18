package com.mgtdev.androidshowcase.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mgtdev.androidshowcase.presentation.home.HomeScreen
import com.mgtdev.androidshowcase.presentation.home.HomeViewModel
import com.mgtdev.androidshowcase.presentation.profile.ProfileScreen
import com.mgtdev.androidshowcase.presentation.profile.ProfileViewModel

@Composable
fun MainTabsNav3(
    onOpenDetail: (String) -> Unit,
    onLogout: () -> Unit,
    onBackAtHome: () -> Unit,
) {
    val tabBackStack = rememberNavBackStack(HomeKey)
    val selectedDestination = tabBackStack.lastOrNull()

    fun selectTab(destination: AppNavKey) {
        if (tabBackStack.lastOrNull() != destination) {
            tabBackStack.clearAndNavigate(destination)
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedDestination == HomeKey,
                    onClick = { selectTab(HomeKey) },
                    icon = { Text(text = "H") },
                    label = { Text(text = "Home") },
                )

                NavigationBarItem(
                    selected = selectedDestination == ProfileKey,
                    onClick = { selectTab(ProfileKey) },
                    icon = { Text(text = "P") },
                    label = { Text(text = "Perfil") },
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            NavDisplay(
                backStack = tabBackStack,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                ),
                onBack = {
                    if (tabBackStack.lastOrNull() == ProfileKey) {
                        tabBackStack.clearAndNavigate(HomeKey)
                    } else {
                        onBackAtHome()
                    }
                },
                entryProvider = entryProvider {
                    entry<HomeKey> {
                        val viewModel: HomeViewModel = hiltViewModel()
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                        HomeScreen(
                            uiState = uiState,
                            onOpenDetail = onOpenDetail,
                            onRetry = viewModel::loadHome,
                        )
                    }

                    entry<ProfileKey> {
                        val viewModel: ProfileViewModel = hiltViewModel()
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                        ProfileScreen(
                            uiState = uiState,
                            onLogoutClick = onLogout,
                            onRetry = viewModel::loadProfile,
                        )
                    }
                }
            )
        }
    }
}
