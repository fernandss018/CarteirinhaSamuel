package com.maysa.carterinhamaysa.app

import android.app.Application
import com.maysa.carterinhamaysa.app.di.AppContainer
import com.maysa.carterinhamaysa.app.di.DefaultAppContainer

class CarteirinhaApplication : Application() {
    val container: AppContainer by lazy {
        DefaultAppContainer()
    }
}