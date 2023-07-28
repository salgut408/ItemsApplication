package com.salvador.myapplication.ui.navigation

sealed class NavigationScreens(val route: String) {
    object MainScreen: NavigationScreens("main_screen")
    object DetailScreen: NavigationScreens("details_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}
