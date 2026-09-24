package com.maysa.carterinhamaysa.feature.login.data.remote.service

import com.maysa.carterinhamaysa.feature.login.data.remote.dto.LoginRequestDto
import com.maysa.carterinhamaysa.feature.login.data.remote.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto
}