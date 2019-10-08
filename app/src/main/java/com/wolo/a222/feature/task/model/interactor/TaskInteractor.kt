package com.wolo.a222.feature.task.model.interactor

import io.reactivex.Flowable

interface TaskInteractor{

    fun getQuestion(number: Int): Flowable<String>

    fun deleteOneQuestion(packId: String, taskId: Int)
}