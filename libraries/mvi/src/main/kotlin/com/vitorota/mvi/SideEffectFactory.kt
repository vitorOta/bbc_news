package com.vitorota.mvi

interface SideEffectFactory<Result, SideEffect> {
    fun produce(result: Result): SideEffect?
}