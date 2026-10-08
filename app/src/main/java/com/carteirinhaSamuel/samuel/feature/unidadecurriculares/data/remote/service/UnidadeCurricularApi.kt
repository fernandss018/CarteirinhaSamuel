package com.maysa.samuel.feature.unidadecurriculares.data.remote.service

import com.maysa.samuel.feature.unidadecurriculares.data.remote.dto.UnidadeCurricularDTO
import retrofit2.http.GET

interface UnidadeCurricularApi {

    @GET("unidades-curriculares")
    suspend fun listarUnidadesCurriculares():
        List<UnidadeCurricularDTO>
}