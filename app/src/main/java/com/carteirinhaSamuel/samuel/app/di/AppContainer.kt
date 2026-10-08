package com.maysa.samuel.app.di

import com.maysa.samuel.core.auth.AuthTokenStore
import com.maysa.samuel.feature.login.data.repository.LoginRepository
import com.maysa.samuel.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository

interface AppContainer {

    val loginRepository: LoginRepository

    val unidadeCurricularRepository: UnidadeCurricularRepository

    val authTokenStore: AuthTokenStore
}