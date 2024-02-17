package com.asabirov.translation.domain

import com.asabirov.core.language.Language

interface TranslationClient {

    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}