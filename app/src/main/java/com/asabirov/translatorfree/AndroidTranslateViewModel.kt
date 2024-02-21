package com.asabirov.translatorfree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asabirov.translation.domain.Translate
import com.asabirov.translation.domain.history.HistoryDataSource
import com.asabirov.translation.presentation.event.TranslateEvent
import com.asabirov.translation.presentation.viewmodel.TranslateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidTranslateViewModel @Inject constructor(
    private val translate: Translate,
    private val historyDataSource: HistoryDataSource
): ViewModel() {

    private val viewModel by lazy {
        TranslateViewModel(
            translate = translate,
            historyDataSource = historyDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: TranslateEvent) {
        viewModel.onEvent(event)
    }
}