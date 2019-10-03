package com.wolo.a222.feature.task.model.Interactor

import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.di.Scope.PerFeature
import io.reactivex.Flowable
import javax.inject.Inject

@PerFeature
class TaskInteractorImpl @Inject constructor() : TaskInteractor{

    override fun getQuestion(): Flowable<String> {
        return Flowable.just(game.getRandomQuestion(game.choosedPack!!.name) )
    }
}