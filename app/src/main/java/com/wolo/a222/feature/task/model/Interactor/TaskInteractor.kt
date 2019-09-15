package com.wolo.a222.feature.task.model.Interactor

import io.reactivex.Flowable
import io.reactivex.Single

interface TaskInteractor{

    fun getQuestion(): Flowable<String>

}