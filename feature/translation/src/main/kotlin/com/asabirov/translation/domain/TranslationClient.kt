package com.asabirov.translation.domain

import com.asabirov.translation.presentation.model.Language

interface TranslationClient {

    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}