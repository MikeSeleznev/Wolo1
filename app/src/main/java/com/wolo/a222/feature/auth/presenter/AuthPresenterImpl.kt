package com.wolo.a222.feature.auth.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Players
import com.wolo.a222.Presenter.BasePresenter
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.auth.model.interactor.AuthInteractor
import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.common.navigation.Navigator
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerScreen
class AuthPresenterImpl
@Inject constructor(
        val navigator: Navigator,
        val authInteractor: AuthInteractor
) : BasePresenter<AuthView>, AuthPresenter {

    private val compositeDisposable = CompositeDisposable()

    private val authSubject = BehaviorRelay.createDefault<AuthState>(AuthState(mutableListOf(), mutableListOf()))

    private var state: AuthState
        set(value) = authSubject.accept(value)
        get() = authSubject.value!!

    private var reverseGamersArray: MutableList<String> = mutableListOf()

    override fun initState() {
        state = AuthState(mutableListOf(), mutableListOf())
    }

    override fun onStartAuth() {
        var a = "a"
    }

    override fun viewState(): Flowable<AuthState> {
        return authSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer()
                .subscribeOn(AndroidSchedulers.mainThread())
    }

    override fun onFinish() {
        compositeDisposable.clear()
    }

    override fun onClickStartPlay(gamers: List<String>) {
        var players = mutableListOf<Players>()
        var num = 1
        for (g in gamers){
            players.add(Players(g, num))
            num+=1
        }

        if (players.size > 0)  game.initDate(players.toTypedArray())
        var layoutResId = when (players.size) {
            2 -> R.layout.gamezone_two
            3 -> R.layout.gamezone_three
            4 -> R.layout.gamezone_four
            5 -> R.layout.gamezone_five
            6 -> R.layout.gamezone_six
            7 -> R.layout.gamezone_seven
            8 -> R.layout.gamezone_eight
            else -> R.layout.fragment_auth}
            navigator.showGameZone(layoutResId)
    }

    override fun addNewPlayer(name: String, gamersArray: MutableList<String>) {
        gamersArray.add(name)
        reverseGamersArray = gamersArray.toMutableList()
        //reverseGamersArray.reverse()
        state = state.copy(gamersArray = gamersArray, reverseGamersArray = reverseGamersArray)
    }
}