package com.wolo.a222.feature.auth.model.interactor

import com.wolo.a222.feature.common.entity.Pack
import com.wolo.a222.feature.common.entity.Players
import com.wolo.a222.feature.common.entity.Purchases
import com.wolo.a222.feature.common.model.TasksVM
import io.reactivex.Flowable


interface AuthInteractor {

    fun activateSuperUser()

    fun getPacks(): Flowable<List<Pack>>

    fun getPurchases(): Flowable<List<Purchases>>

    fun isSuperUser(): Boolean

    fun setTasksVM(tasksVM: List<TasksVM>)

    fun initDate(players: List<Players>)

    fun getPlayers(): List<Players>

    fun setPlayers(players: List<Players>)
}