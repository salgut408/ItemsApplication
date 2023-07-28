package com.salvador.myapplication.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salvador.myapplication.ui.app.ItemAppState
import com.salvador.myapplication.ui.screens.detail_screen.DetailScreen
import com.salvador.myapplication.ui.screens.main_screen.MainScreen

@Composable
fun Navigation(
    appState: ItemAppState,
    padding: PaddingValues
) {
    NavHost(
        navController = appState.navController,
        startDestination = NavigationScreens.MainScreen.route,

        ) {
        composable(
            route = NavigationScreens.MainScreen.route
        ) {
            MainScreen(
                navController = appState.navController,
                modifier = Modifier.padding(padding),
            )
        }
        composable(
            route = NavigationScreens.DetailScreen.route + "/{itemDesc}",
            arguments = listOf(
                navArgument("itemDesc") {
                    type = NavType.StringType
                })
        ) {
            entry ->
            val itemDesc = entry.arguments?.getString("itemDesc")
            if (itemDesc != null) {
                DetailScreen(
                    modifier = Modifier,
                    itemDesc = itemDesc
                )
            }
        }
    }
}