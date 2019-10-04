package com.wolo.a222.feature.common.navigation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

interface Navigator {

    fun attachActivity(activity: AppCompatActivity)

    fun detachActivity(activity: AppCompatActivity)

    fun onStart()

    fun onStartMainActivity()

    //fun onBackPressed(): Boolean

    fun onFinish(fragment: Fragment)

    fun startHomeLauncher(context: Context)

    fun startGame()

    fun showGameZone(id: Int)

    fun showDecks()

    fun showSelectTask()

    fun showTask()

    fun doneTask(id: Int)

    fun showShop()

    fun closeShop()
}