package com.vitorota.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class MviViewModel<Intent, Result, ViewState, SideEffect>(
    initialViewState: ViewState,
    private val defaultErrorViewState: ViewState,
    private val resultFactory: ResultFactory<Intent, Result>,
    private val viewStateFactory: ViewStateFactory<Result, ViewState>,
    private val sideEffectFactory: SideEffectFactory<Result, SideEffect>
) : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(initialViewState)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val _sideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> = _sideEffect.receiveAsFlow()

    fun dispatch(intent: Intent) {
        viewModelScope.launch {
            resultFactory.process(intent)
                .catch {
                    Log.e("OLHAQUI", "catch  + ${it.message}", it)
                    _viewState.update { defaultErrorViewState }
                }
                .collect { result ->
                    val newViewState = viewStateFactory.reduce(result, _viewState.value)
                    _viewState.update { newViewState }

                    val sideEffect = sideEffectFactory.produce(result)
                    sideEffect?.let { _sideEffect.send(it) }
                }
        }
    }
}
