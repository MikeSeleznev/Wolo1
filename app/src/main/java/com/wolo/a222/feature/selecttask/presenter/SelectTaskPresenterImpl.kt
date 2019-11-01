package com.wolo.a222.feature.selecttask.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.Const
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.repository.WoloRepository
import com.wolo.a222.feature.common.storage.WoloDatabase
import com.wolo.a222.feature.selecttask.model.interactor.SelectTaskInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SelectTaskPresenterImpl
    @Inject constructor(
          private val navigator: Navigator,
          private val selectTaskInteractor: SelectTaskInteractor
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
        val selectTaskVM = selectTaskInteractor.getPacks().map {
            SelectTaskVM(it.id, it.namePack, it.quantity, it.urlImage, it.quantityNow, it.isBought)
        }
        setViewState(selectTaskVM)
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
            selectTaskInteractor.setChoosedPack(p)
            navigator.showTask()
    }
}