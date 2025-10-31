package com.vitorota.features.news

import android.app.Activity
import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.AuthenticationCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

fun requestBiometry(activity: FragmentActivity, onSuccess: () -> Unit, onFail: () -> Unit) {
    val executor = ContextCompat.getMainExecutor(activity)
    val biometricPrompt = BiometricPrompt(activity,executor, object : AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
            onSuccess()
        }

        override fun onAuthenticationFailed() {
            onFail()
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            //TODO handle error
            onFail()
        }
    })

    //TODO get strings from arguments or resources
    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometry")
        .setSubtitle("Use your biometry")
        .setNegativeButtonText("Cancel")
        .build()

    biometricPrompt.authenticate(promptInfo)
}