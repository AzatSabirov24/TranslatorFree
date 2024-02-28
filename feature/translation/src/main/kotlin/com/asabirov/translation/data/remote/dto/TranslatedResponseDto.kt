package com.asabirov.translation.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TranslatedResponseDto(
    val data: TranslationsDto
)
