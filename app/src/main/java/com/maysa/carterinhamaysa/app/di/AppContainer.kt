package com.maysa.carterinhamaysa.app.di

import com.maysa.carterinhamaysa.core.auth.AuthTokenStore
import com.maysa.carterinhamaysa.feature.login.data.repository.LoginRepository
import com.maysa.carterinhamaysa.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository

interface AppContainer {

    val loginRepository: LoginRepository

    val unidadeCurricularRepository: UnidadeCurricularRepository

    val authTokenStore: AuthTokenStore
}