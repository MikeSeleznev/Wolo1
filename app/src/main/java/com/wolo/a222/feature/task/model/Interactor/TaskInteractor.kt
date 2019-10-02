package com.wolo.a222.feature.task.model.Interactor

import io.reactivex.Flowable

interface TaskInteractor{

    fun getQuestion(): Flowable<String>

}