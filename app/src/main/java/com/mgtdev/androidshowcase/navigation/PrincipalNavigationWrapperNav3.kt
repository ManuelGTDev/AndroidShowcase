package com.mgtdev.androidshowcase.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.mgtdev.androidshowcase.presentation.detail.DetailScreen
import com.mgtdev.androidshowcase.presentation.detail.DetailViewModel

@Composable
fun PrincipalNavigationWrapperNav3(
    onLogout: () -> Unit,
) {
    val activity = LocalActivity.current
    val backStack = rememberNavBackStack(MainTabsKey)

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        onBack = {
            if (backStack.size > 1) {
                backStack.back()
            } else {
                activity?.finish()
            }
        },
        entryProvider = entryProvider {
            entry<MainTabsKey> {
                MainTabsNav3(
                    onOpenDetail = { detailId ->
                        backStack.navigateSingleTop(DetailKey(id = detailId))
                    },
                    onLogout = onLogout,
                    onBackAtHome = {
                        activity?.finish()
                    }
                )
            }

            entry<DetailKey> {
                val viewModel: DetailViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                DetailScreen(
                    uiState = uiState,
                    onBack = { backStack.back() },
                    onRetry = viewModel::loadProducts,
                )
            }
        }
    )
}
