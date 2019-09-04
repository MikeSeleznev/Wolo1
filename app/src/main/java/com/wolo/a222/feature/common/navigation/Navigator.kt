package com.wolo.a222.feature.common.navigation

import androidx.fragment.app.Fragment
import android.content.Context
import androidx.appcompat.app.AppCompatActivity

interface Navigator {

    fun attachActivity(activity: AppCompatActivity)

    fun detachActivity(activity: AppCompatActivity)

    fun onStart()

    fun onStartMainActivity()

    //fun onBackPressed(): Boolean

    fun onFinish(fragment: Fragment)

    fun startHomeLauncher(context: Context)

    fun startGame()

    fun showGameZone()

    fun showDecks()

    fun showTask()
}