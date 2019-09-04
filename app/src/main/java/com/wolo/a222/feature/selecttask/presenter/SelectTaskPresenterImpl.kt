package com.wolo.a222.feature.selecttask.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Presenter.BasePresenter
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SelectTaskPresenterImpl
    @Inject constructor(
           val navigator: Navigator
    ): BasePresenter<SelectTaskView>, SelectTaskPresenter{
    private val compositeDisposable = CompositeDisposable()

    private val gameZoneSubject = BehaviorRelay.createDefault(SelectTaskState())

    private var state: SelectTaskState
        set(value) = gameZoneSubject.accept(value)
        get() = gameZoneSubject.value!!

    override fun initState() {
        state = SelectTaskState()
    }

    override fun onFinish(){
        compositeDisposable.clear()
    }

    override fun viewState(): Flowable<SelectTaskState> {
        return gameZoneSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
    }

    override fun setIntLeftCards() {
        val selectTaskV = SelectTaskVM(kolodaNumCards1 = game.cards[0].leftCardsInt(),
                kolodaNumCards2 = game.cards[1].leftCardsInt(),
                kolodaNumCards3 = game.cards[2].leftCardsInt(),
                kolodaNumCards4 = game.cards[3].leftCardsInt()
                //kolodaNumCards5 = game.cards[4].leftCardsText()
        )

        state = state.copy(selectTask = selectTaskV)
    }

    override fun showTask(){
        navigator.showTask()
    }
}