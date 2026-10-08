package com.maysa.samuel.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.maysa.samuel.app.di.AppContainer
import com.maysa.samuel.app.navigation.AppNavHost
import com.maysa.samuel.core.designsystem.theme.CarteirinhaDigital2DEVEST_BTheme

@Composable
fun App(container: AppContainer) {
    CarteirinhaDigital2DEVEST_BTheme() {
        val navController = rememberNavController()
        AppNavHost(
            navController = navController,
            container = container
        )
    }
}