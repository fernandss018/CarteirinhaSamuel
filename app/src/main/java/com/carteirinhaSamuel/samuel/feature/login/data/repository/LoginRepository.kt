package com.maysa.samuel.feature.login.data.repository

import com.maysa.samuel.feature.login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login( usuario:String, senha:String): Result<UsuarioLogado>
}