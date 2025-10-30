package com.vitorota.mvi

import kotlinx.coroutines.flow.Flow

interface ResultFactory<Intent, Result> {
    fun process(intent: Intent): Flow<Result>
}