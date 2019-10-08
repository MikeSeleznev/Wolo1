package com.wolo.a222.feature.task.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface TaskPresenter: BasePresenter<TaskView> {

    fun viewState(): Flowable<TaskState>

    fun initState()

    fun doneButtonOnClick()

    fun deleteOneQuestion()
}

interface TaskView: View


data class TaskState(
        val task: String = "",
        val taskTheme: String = "",
        val leftCards: String = "",
        val packId: String = "",
        val taskId : Int = 0
)