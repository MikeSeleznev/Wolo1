package com.wolo.a222.feature.task.presenter

import android.annotation.SuppressLint
import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.task.model.Interactor.TaskInteractor
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TaskPresenterImpl @Inject constructor(
        val navigator: Navigator,
        val interactor: TaskInteractor
) : BasePresenter<TaskView>, TaskPresenter {

    private val compositeDisposable = CompositeDisposable()
    private val taskSubject = BehaviorRelay.createDefault(TaskState())

    private var state: TaskState
        set(value) = taskSubject.accept(value)
        get() = taskSubject.value!!

    override fun initState() {
        state = TaskState()
    }

    override fun onFinish() {
        compositeDisposable.clear()
    }


    override fun viewState(): Flowable<TaskState> {
        return taskSubject.toFlowable(BackpressureStrategy.LATEST)
                .onBackpressureBuffer(3)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                        getQuestion()
                }
    }

    @SuppressLint("CheckResult")
    private fun getQuestion(){
        interactor.getQuestion()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    setViewState(it)
                }
    }

    private fun setViewState(task: String) {
        state = state.copy(task = task, taskTheme = game.choosedPack!!.name)
    }

    override fun doneButtonOnClick() {
        game.choosedPack?.name?.let { game.minusOneCard(it) }
        val layoutResId = when (game.players.size) {
            2 -> R.layout.gamezone_two
            3 -> R.layout.gamezone_three
            4 -> R.layout.gamezone_four
            5 -> R.layout.gamezone_five
            6 -> R.layout.gamezone_six
            7 -> R.layout.gamezone_seven
            8 -> R.layout.gamezone_eight
            else -> R.layout.fragment_auth}
        navigator.doneTask(layoutResId)
    }
}