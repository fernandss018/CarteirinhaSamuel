package com.maysa.carterinhamaysa.feature.unidadecurriculares.domain.repository

import com.maysa.carterinhamaysa.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listarUnidadesCurriculares(): Result<List<UnidadeCurricular>>
}