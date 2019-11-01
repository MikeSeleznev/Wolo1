package com.wolo.a222.feature.selecttask.model.interactor

import com.wolo.a222.feature.common.model.TasksVM
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM

interface SelectTaskInteractor {

    fun setChoosedPack(p: SelectTaskVM)

    fun getPacks(): List<TasksVM>

}