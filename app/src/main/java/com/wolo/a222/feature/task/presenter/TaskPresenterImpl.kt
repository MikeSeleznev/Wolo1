package com.wolo.a222.feature.task.presenter

import android.annotation.SuppressLint
import com.jakewharton.rxrelay2.BehaviorRelay
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.navigation.Navigator
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
) : TaskPresenter {

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
                .subscribe{it ->
                    setViewState(it)
                }
    }

    private fun setViewState(task: String) {
        state = state.copy(task = task, taskTheme = game.choosedPack.name)
    }

    override fun doneButtonOnClick() {
        navigator.doneTask()
    }
}