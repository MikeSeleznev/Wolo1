package com.wolo.a222.feature.task.presenter

import com.wolo.a222.feature.common.presenter.Presenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface TaskPresenter: Presenter<TaskView>{

    fun viewState(): Flowable<TaskState>

    fun initState()

    fun doneButtonOnClick()
}

interface TaskView: View


data class TaskState(
        val task: String = "",
        val taskTheme: String = ""
)