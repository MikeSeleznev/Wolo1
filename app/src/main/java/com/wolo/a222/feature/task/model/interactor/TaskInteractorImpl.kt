package com.wolo.a222.feature.task.model.interactor

import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.Scope.PerFeature
import io.reactivex.Flowable
import javax.inject.Inject

@PerFeature
class TaskInteractorImpl @Inject constructor() : TaskInteractor{

    override fun getQuestion(number : Int): Flowable<String> {
        return Flowable.just(game.getRandomQuestion(number))
    }

    override fun deleteOneQuestion(packId: String, taskId: Int) {
        val p = game.packs.findLast { it.id == packId }
        p?.tasks?.map {
            it != taskId.toString()
        }
        //p?.tasks?.removeAt(taskId)
    }
}