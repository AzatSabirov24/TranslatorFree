package com.asabirov.translation.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TranslationsDto(
    val translations: List<TranslatedDto> = emptyList()
)
