package com.maysa.carterinhamaysa.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.maysa.carterinhamaysa.app.di.AppContainer
import com.maysa.carterinhamaysa.app.navigation.AppNavHost
import com.maysa.carterinhamaysa.core.designsystem.theme.CarteirinhaDigital2DEVEST_BTheme

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