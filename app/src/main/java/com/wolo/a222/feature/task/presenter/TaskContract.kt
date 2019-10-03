package com.wolo.a222.feature.task.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface TaskPresenter: BasePresenter<TaskView> {

    fun viewState(): Flowable<TaskState>

    fun initState()

    fun doneButtonOnClick()
}

interface TaskView: View


data class TaskState(
        val task: String = "",
        val taskTheme: String = ""
)