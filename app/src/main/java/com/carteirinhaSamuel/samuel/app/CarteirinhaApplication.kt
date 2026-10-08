package com.maysa.samuel.app

import android.app.Application
import com.maysa.samuel.app.di.AppContainer
import com.maysa.samuel.app.di.DefaultAppContainer

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}