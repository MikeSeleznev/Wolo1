package com.wolo.a222.feature.selecttask.presenter

import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.presenter.BasePresenter
import com.wolo.a222.feature.common.presenter.View
import com.wolo.a222.model.sku.SkuDeck
import io.reactivex.Flowable

interface SelectTaskPresenter: BasePresenter<SelectTaskView>{

    fun initState()

    fun viewState(): Flowable<SelectTaskState>

    fun setIntLeftCards()

    fun showSelectTask()

    fun showTask(p : String)

    fun getPacks()
}

interface SelectTaskView: View

data class SelectTaskState(
        val taskList: List<SelectTaskVM> = emptyList()
)

data class SelectTaskVM(
        val id: String = "",
        val namePack: String = "",
        val quantity: Int = 0,
        val urlImage: String = "",
        val quantityNow: Int
)