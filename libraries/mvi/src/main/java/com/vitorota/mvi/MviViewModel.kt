package com.vitorota.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MviViewModel<Intent, Result, ViewState>(
    initialViewState: ViewState,
    private val resultFactory: ResultFactory<Intent, Result>,
    private val viewStateFactory: ViewStateFactory<Result, ViewState>
) : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(initialViewState)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    fun handle(intent: Intent) {
        //TODO handle exceptions
        viewModelScope.launch {
            resultFactory.process(intent)
                .map { viewStateFactory.reduce(it, _viewState.value) }
                .collect { _viewState.update { it } }
        }
    }
    //TODO side effects
}