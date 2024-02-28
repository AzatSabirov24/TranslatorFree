package com.asabirov.translation.data.remote

import com.asabirov.core.network.NetworkConstants
import com.asabirov.core.network.NetworkError
import com.asabirov.core.network.TranslateException
import com.asabirov.translation.data.remote.dto.TranslateDto
import com.asabirov.translation.data.remote.dto.TranslatedDto
import com.asabirov.translation.data.remote.dto.TranslatedResponseDto
import com.asabirov.translation.domain.TranslationClient
import com.asabirov.translation.presentation.model.Language
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
    ): TranslatedDto {
        val result = try {
//            httpClient.sendPipeline.intercept(HttpSendPipeline.S) {
//                context.headers.append("key", "AIzaSyB6U7GfHE4Wn2UpUmTd7QHM68CyMzKqC98")
//            }
            httpClient.post {
                url(NetworkConstants.BASE_URL + "/language/translate/v2?key=")
//                header("key", "AIzaSyB6U7GfHE4Wn2UpUmTd7QHM68CyMzKqC98")
                contentType(ContentType.Application.Json)
                setBody(
                    TranslateDto(
                        textToTranslate = fromText,
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
            result.body<TranslatedResponseDto>().data.translations.first()
        } catch (e: Exception) {
            println("qqq KtorTranslationClient->translate->${e.message}")
            throw TranslateException(error = NetworkError.SERVER_ERROR)
        }
    }
}