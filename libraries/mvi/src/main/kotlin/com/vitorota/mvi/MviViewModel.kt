package com.vitorota.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MviViewModel<Intent, Result, ViewState>(
    initialViewState: ViewState,
    private val defaultErrorViewState: ViewState,
    private val resultFactory: ResultFactory<Intent, Result>,
    private val viewStateFactory: ViewStateFactory<Result, ViewState>
) : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(initialViewState)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    fun dispatch(intent: Intent) {
        viewModelScope.launch {
            resultFactory.process(intent)
                .catch {
                    Log.e("OLHAQUI", "catch  + ${it.message}", it)
                    _viewState.update { defaultErrorViewState }
                }
                .collect { result ->
                    _viewState.update { currentState ->
                        viewStateFactory.reduce(result, currentState)
                    }
                }
        }
    }
    //TODO side effects
}