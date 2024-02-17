package com.asabirov.translation.data.remote

import com.asabirov.core.network.NetworkConstants
import com.asabirov.core.network.TranslateException
import com.asabirov.core.language.Language
import com.asabirov.core.network.NetworkError
import com.asabirov.translation.data.remote.dto.TranslatedDto
import com.asabirov.translation.domain.TranslationClient
import com.asabirov.translator.translate.data.translate.TranslateDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.utils.io.errors.IOException

class KtorTranslationClient(
    private val httpClient: HttpClient
) : TranslationClient {

    override suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String {
        val result = try {
            httpClient.post {
                url(NetworkConstants.BASE_URL + "/translate")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        textToTranslate = fromText,
                        sourceLanguageCode = fromLanguage.langCode,
                        targetLanguageCode = toLanguage.langCode
                    )
                )
            }
        } catch (e: IOException) {
            throw TranslateException(error = NetworkError.SERVICE_UNAVAILABLE)
        }

        when (result.status.value) {
            in 200..299 -> Unit
            500 -> throw TranslateException(NetworkError.SERVER_ERROR)
            in 400..499 -> throw TranslateException(NetworkError.CLIENT_ERROR)
            else -> throw TranslateException(NetworkError.UNKNOWN_ERROR)
        }

        return try {
            result.body<TranslatedDto>().translatedText
        } catch (e: Exception) {
            throw TranslateException(error = NetworkError.SERVER_ERROR)
        }
    }
}