package com.mgtdev.androidshowcase.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppNavKey : NavKey

@Serializable
data object LoginKey : AppNavKey

@Serializable
data object PrincipalWrapperKey : AppNavKey

@Serializable
data object MainTabsKey : AppNavKey

@Serializable
data class DetailKey(
    val id: String,
) : AppNavKey

@Serializable
data object HomeKey : AppNavKey

@Serializable
data object ProfileKey : AppNavKey

inline fun <reified T : NavKey> NavBackStack<NavKey>.navigateSingleTop(
    destination: T,
) {
    val currentDestination = lastOrNull()

    if (currentDestination is T && currentDestination == destination) {
        return
    }

    add(destination)
}

fun NavBackStack<NavKey>.clearAndNavigate(destination: NavKey) {
    clear()
    add(destination)
}

fun NavBackStack<NavKey>.back() {
    if (size > 1) {
        removeLastOrNull()
    }
}