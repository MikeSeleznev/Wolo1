package com.wolo.a222.feature.splashscreen.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface SplashScreenPresenter : BasePresenter<SplashScreenView> {

    fun initState()

    fun viewState(): Flowable<SplashScreenState>

    fun loadDate(version: Long)

    fun loadInfo()

}

interface SplashScreenView: View

data class SplashScreenState(
       var dateIsLoaded: Boolean = false
)