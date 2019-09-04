package com.wolo.a222.feature.auth.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Players
import com.wolo.a222.Presenter.BasePresenter
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.auth.model.interactor.AuthInteractor
import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.common.navigation.Navigator
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerScreen
class AuthPresenterImpl
@Inject constructor(
        val navigator: Navigator,
        val authInteractor: AuthInteractor
) : BasePresenter<AuthView>, AuthPresenter {

    private val compositeDisposable = CompositeDisposable()

    private val authSubject = BehaviorRelay.createDefault<AuthState>(AuthState(true))

    private var state: AuthState
        set(value) = authSubject.accept(value)
        get() = authSubject.value!!

    override fun initState() {
        state = AuthState(true)
    }

    override fun onStartAuth() {
        var a = "a"
    }

    override fun viewState(): Flowable<AuthState> {
        return authSubject.toFlowable(BackpressureStrategy.LATEST)
    }

    override fun onFinish() {
        compositeDisposable.clear()
    }

    override fun onClickStartPlay(gamers: List<String>) {
        var players = mutableListOf<Players>()
        var num = 1
        for (g in gamers){
            players.add(Players(g.toString(), num))
            num+=1
        }

        if (players.size > 0)  game.initDate(players.toTypedArray())
            navigator.showGameZone()
    }
}