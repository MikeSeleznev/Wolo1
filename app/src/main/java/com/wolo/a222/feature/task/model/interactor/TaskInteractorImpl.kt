package com.wolo.a222.feature.task.model.interactor

import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.scope.PerFeature
import io.reactivex.Flowable
import javax.inject.Inject

@PerFeature
class TaskInteractorImpl @Inject constructor() : TaskInteractor{

    override fun getQuestion(number : Int): Flowable<String> {
        return Flowable.just(game.getRandomQuestion(number))
    }

    override fun deleteOneQuestion(packId: String, taskId: Int) {
       game.tasksVM.findLast { it.id == packId }
            .let {
                if (it != null){
                val a = it.tasks.filterIndexed { index, _ -> index != taskId }
                it.tasks = a
                it.quantityNow = it.tasks.size}
            }
    }
}