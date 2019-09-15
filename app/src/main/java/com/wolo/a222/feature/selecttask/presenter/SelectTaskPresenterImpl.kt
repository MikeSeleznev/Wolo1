package com.wolo.a222.feature.selecttask.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Presenter.BasePresenter
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.model.Cards
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.selecttask.model.Interactor.SelectTaskInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.Any as Any1

class SelectTaskPresenterImpl
    @Inject constructor(
           val navigator: Navigator,
           val interactor: SelectTaskInteractor
    ): BasePresenter<SelectTaskView>, SelectTaskPresenter{
    private val compositeDisposable = CompositeDisposable()

    private val selectTaskSubject = BehaviorRelay.createDefault(SelectTaskState())

    private var state: SelectTaskState
        set(value) = selectTaskSubject.accept(value)
        get() = selectTaskSubject.value!!

    private lateinit var pack: Cards

    override fun initState() {
        state = SelectTaskState()
    }

    override fun onFinish(){
        compositeDisposable.clear()
    }

    override fun viewState(): Flowable<SelectTaskState> {
        return selectTaskSubject.toFlowable(BackpressureStrategy.LATEST)
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

    override fun showSelectTask(){
        navigator.showSelectTask()
    }

    override fun showTask(namePack: String) {

        for (p in game.cards){
            if (namePack == p.name){
                pack = p
            }
        }
        interactor.setChoosedPack(pack)
        navigator.showTask()
    }
}