package com.wolo.a222.feature.selecttask.presenter

import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import io.reactivex.Flowable

interface SelectTaskPresenter: BasePresenter<SelectTaskView>{

    fun initState()

    fun viewState(): Flowable<SelectTaskState>

    fun setIntLeftCards()

    fun showSelectTask()

    fun showTask(pack : String)
}

interface SelectTaskView: View

data class SelectTaskState(
        val selectTask: SelectTaskVM? = null
)

data class SelectTaskVM(
        var kolodaNumCards1: String = "",
        var kolodaNumCards2: String = "",
        var kolodaNumCards3: String = "",
        var kolodaNumCards4: String = "",
        var kolodaNumCards5: String = ""
)