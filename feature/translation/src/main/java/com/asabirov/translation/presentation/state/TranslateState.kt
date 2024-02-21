package com.asabirov.translation.presentation.state

import com.asabirov.core.network.NetworkError
import com.asabirov.translation.presentation.model.UiHistoryItem
import com.asabirov.translation.presentation.model.UiLanguage


data class TranslateState(
    val fromText: String = "",
    val toText: String? = null,
    val isTranslating: Boolean = false,
    val fromLanguage: UiLanguage = UiLanguage.byCode("en"),
    val toLanguage: UiLanguage = UiLanguage.byCode("de"),
    val isChoosingFromLanguage: Boolean = false,
    val isChoosingToLanguage: Boolean = false,
    val error: NetworkError? = null,
    val history: List<UiHistoryItem> = emptyList()
)