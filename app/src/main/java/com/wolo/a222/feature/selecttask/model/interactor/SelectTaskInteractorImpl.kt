package com.wolo.a222.feature.selecttask.model.interactor

import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.scope.PerFeature
import com.wolo.a222.feature.common.model.TasksVM
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM
import javax.inject.Inject

@PerFeature
class SelectTaskInteractorImpl @Inject constructor(
) : SelectTaskInteractor {

    override fun setChoosedPack(p: SelectTaskVM) {
       game.choosedPack = p
    }

    override fun getPacks(): List<TasksVM> {
        return game.tasksVM
    }
}