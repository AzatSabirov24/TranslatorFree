package com.asabirov.translation.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TranslatedDto(
    val translatedText: String
)
