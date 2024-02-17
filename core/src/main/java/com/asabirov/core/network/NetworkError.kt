package com.asabirov.core.network

enum class NetworkError {

    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    UNKNOWN_ERROR
}

class TranslateException(val error: NetworkError) : Exception(
    "An error occurred when translating: $error"
)