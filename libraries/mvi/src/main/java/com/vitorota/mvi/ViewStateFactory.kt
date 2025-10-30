package com.vitorota.mvi

interface ViewStateFactory<Result, ViewState> {
    fun reduce(result: Result, previousViewState: ViewState): ViewState
}