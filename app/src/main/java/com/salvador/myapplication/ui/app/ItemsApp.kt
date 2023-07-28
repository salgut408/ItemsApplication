package com.salvador.myapplication.ui.app

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salvador.myapplication.ui.navigation.Navigation
import kotlinx.coroutines.CoroutineScope

@Composable
fun ItemsApp(
) {
    val appState = rememberAppState()
    Scaffold() { innerPadding ->
        Navigation(appState, padding = innerPadding)
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    ) = remember (navController,) {
        ItemAppState(navController)
}