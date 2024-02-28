package com.asabirov.translation.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TranslateDto(
    @SerialName("q")
    val textToTranslate: String,
    @SerialName("source")
    val sourceLanguageCode: String? = null,
    @SerialName("target")
    val targetLanguageCode: String
)
