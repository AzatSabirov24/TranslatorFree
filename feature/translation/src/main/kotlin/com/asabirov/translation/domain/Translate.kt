package com.asabirov.translation.domain

import com.asabirov.core.Resource
import com.asabirov.core.network.TranslateException
import com.asabirov.translation.data.remote.dto.TranslatedDto
import com.asabirov.translation.domain.history.HistoryDataSource
import com.asabirov.translation.domain.history.HistoryItem
import com.asabirov.translation.presentation.model.Language

class Translate(
    private val client: TranslationClient,
    private val historyDataSource: HistoryDataSource
) {

    suspend fun execute(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Resource<TranslatedDto> {
        return try {
            val translated = client.translate(
                fromLanguage, fromText, toLanguage
            )
            historyDataSource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translated.translatedText,
                )
            )
            Resource.Success(translated)
        } catch(e: TranslateException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}