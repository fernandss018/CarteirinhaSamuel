package com.maysa.samuel.feature.unidadecurriculares.domain.repository

import com.maysa.samuel.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listarUnidadesCurriculares(): Result<List<UnidadeCurricular>>
}