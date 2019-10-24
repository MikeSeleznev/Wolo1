package com.wolo.a222.feature.selecttask.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Const
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.selecttask.model.interactor.SelectTaskInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SelectTaskPresenterImpl
    @Inject constructor(
           val navigator: Navigator,
           val interactor: SelectTaskInteractor
    ): BasePresenter<SelectTaskView>, SelectTaskPresenter{

    companion object {
        private val INITIAL_STATE = SelectTaskState()
    }

    private val compositeDisposable = CompositeDisposable()

    private val selectTaskSubject = BehaviorRelay.createDefault(INITIAL_STATE)

    private var state: SelectTaskState
        set(value) = selectTaskSubject.accept(value)
        get() = selectTaskSubject.value!!


    override fun onFinish(){
        compositeDisposable.clear()
    }

    override fun viewState(): Flowable<SelectTaskState> {
        return selectTaskSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
    }

    override fun getPacks(){
        var isBoughtAll = false

        if (game.superUser) isBoughtAll = true

        interactor.getPurchase().map { purchases ->
            purchases.find { it.sku == Const.alldecksSKU }.let {
                if (it != null) {
                    isBoughtAll = true
                }
            }
            val packs = game.packs.filter { it.priority > 0 }.sortedBy {
                it.priority
            }
            if (isBoughtAll) {
                packs.map { pack ->
                    SelectTaskVM(pack.id, pack.name, pack.restTasks, pack.activeImage, pack.tasks.size, isBoughtAll)
                }
            } else {
                packs.map { pack ->
                    val purchase = purchases.find { it.sku == pack.id }
                    if (purchase != null) {
                        SelectTaskVM(pack.id, pack.name, pack.restTasks, pack.nonActiveImage, pack.tasks.size)
                    } else {
                        SelectTaskVM(pack.id, pack.name, pack.restTasks, pack.activeImage, pack.tasks.size, isBoughtAll)
                    }
                }
            }
        }
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    setViewState(it)
                }.also {
                    compositeDisposable.add(it)
                }
    }

    private fun setViewState(taskList: List<SelectTaskVM>){
        state = state.copy(taskList = taskList)
    }
    override fun setIntLeftCards() {
      /*  val selectTaskV = SelectTaskVM(
                kolodaNumCards1 = game.cards[0].leftCardsInt(),
                kolodaNumCards2 = game.cards[1].leftCardsInt(),
                kolodaNumCards3 = game.cards[2].leftCardsInt(),
                kolodaNumCards4 = game.cards[3].leftCardsInt(),
                kolodaNumCards5 = game.cards[4].leftCardsInt()
        )

        state = state.copy(selectTask = selectTaskV)*/
    }

    override fun showSelectTask(){
        navigator.showSelectTask()
    }

    override fun showTask(p: SelectTaskVM) {
            interactor.setChoosedPack(p)
            navigator.showTask()

    }
}