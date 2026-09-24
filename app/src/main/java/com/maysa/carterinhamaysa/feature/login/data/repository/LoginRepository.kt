package com.maysa.carterinhamaysa.feature.login.data.repository

import com.maysa.carterinhamaysa.feature.login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login( usuario:String, senha:String): Result<UsuarioLogado>
}