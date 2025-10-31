package com.vitorota.features.news

import com.vitorota.features.news.di.prepareNewsModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class DITest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkAppModule() {
        prepareNewsModule("country", "provider").verify()
    }
}