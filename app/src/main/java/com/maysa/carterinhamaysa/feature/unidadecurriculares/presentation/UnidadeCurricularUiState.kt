package com.maysa.carterinhamaysa.feature.unidadecurriculares.presentation

import com.maysa.carterinhamaysa.feature.unidadecurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val listaUnidadesCurriculares: List<UnidadeCurricular> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}