package com.wolo.a222.feature.auth.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Const
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.auth.model.interactor.AuthInteractor
import com.wolo.a222.feature.common.di.Scope.PerScreen
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Players
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.model.TasksVM
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@PerScreen
class AuthPresenterImpl
@Inject constructor(
        val navigator: Navigator,
        private val authInteractor: AuthInteractor
) : BasePresenter<AuthView>, AuthPresenter {

    private val compositeDisposable = CompositeDisposable()

    private val authSubject = BehaviorRelay.createDefault<AuthState>(AuthState(mutableListOf(), mutableListOf()))

    private var state: AuthState
        set(value) = authSubject.accept(value)
        get() = authSubject.value!!

    private var gamersArray: MutableList<String> = mutableListOf()
    private var reverseGamersArray: MutableList<String> = mutableListOf()

    override fun initState() {
        state = AuthState(mutableListOf(), mutableListOf())
    }

    override fun onStartAuth() {

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

        prepareData()

        val players = mutableListOf<Players>()
        var num = 1
        for (g in gamers){
            players.add(Players(g, num))
            num+=1
        }

        if (players.size > 0)  game.initDate(players)
        val layoutResId = when (players.size) {
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

    override fun addNewPlayer(name: String) {
        val gamers = state.gamersArray.toMutableList()
        gamers.add(name)
        reverseGamersArray = gamersArray.toMutableList()
        //reverseGamersArray.reverse()

        state = state.copy(gamersArray = gamers, reverseGamersArray = reverseGamersArray)
    }

    override fun deletePlayer(id: Int) {
        val newArray = state.gamersArray.toMutableList()
        newArray.removeAt(id)
        state = state.copy(gamersArray = newArray)
    }

    override fun activeSuperUser() {
        authInteractor.activateSuperUser()
    }

    private fun prepareData(){
        var isBoughtAll = game.superUser

        Flowable.combineLatest(
            authInteractor.getPurchases(),
            authInteractor.getPacks(),
            BiFunction { purchases: List<Purchases>, packs: List<Pack> ->
                val filteredPacks = packs.filter { it.priority > 0 }.sortedBy { it.priority }
                purchases.find { it.id == Const.alldecksSKU }.let {
                    if (it != null) {
                        isBoughtAll = true
                    }
                }
                if (isBoughtAll) {
                    filteredPacks.map { pack ->
                        TasksVM(pack.id, pack.name, pack.restTasks, pack.activeImage, pack.tasks.size, isBoughtAll, pack.tasks)
                    }
                } else {
                    filteredPacks.map { pack ->
                        val purchase = purchases.find { it.id == pack.id }
                        if (purchase != null) {
                            TasksVM(pack.id, pack.name, pack.restTasks, pack.activeImage, pack.tasks.size, true, pack.tasks)
                        } else {
                            TasksVM(pack.id, pack.name, pack.restTasks, pack.nonActiveImage, pack.tasks.size, pack.alwaysActive, pack.tasks)
                        }
                    }
                }
            })
            .onBackpressureBuffer(3)
            .subscribeOn(Schedulers.io())
            .subscribe {
                setData(it)
            }.also {
                compositeDisposable.add(it)
            }
    }

    private fun setData(data: List<TasksVM>){
        game.tasksVM = data
    }
}