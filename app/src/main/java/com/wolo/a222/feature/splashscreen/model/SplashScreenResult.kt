package com.wolo.a222.feature.splashscreen.model

sealed class SplashScreenResult {
    object SplashScreenCompleted : SplashScreenResult()
    data class SplashScreenFailed(
        val message: String = "",
        val exception: Throwable? = null
    ) : SplashScreenResult()
}
