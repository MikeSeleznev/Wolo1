package com.wolo.a222.feature.common.navigation


import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import androidx.fragment.app.transaction
import androidx.appcompat.app.AppCompatActivity
import com.wolo.a222.R
import com.wolo.a222.feature.auth.view.AuthFragment
import com.wolo.a222.feature.common.di.Scope.PerApplication
import com.wolo.a222.feature.gamezone.view.GameZoneFragment
import com.wolo.a222.feature.selecttask.view.SelectTaskFragment
import com.wolo.a222.feature.splashscreen.view.SplashScreenFragment
import javax.inject.Inject


@PerApplication
class NavigatorImpl
    @Inject constructor(): Navigator {

    private var activity: AppCompatActivity? = null

    private val fragmentManager
        get() = activity?.supportFragmentManager



    private val currentFragment
        get() = fragmentManager?.findFragmentById(R.id.content)


    override fun attachActivity(activity: AppCompatActivity) {
        this.activity = activity
    }

    override fun detachActivity(activity: AppCompatActivity) {
        if (this.activity == activity) {
            this.activity = null
        }
    }


    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) = runOnUiThread {
        fragmentManager?.transaction {
            replace(R.id.content, fragment)
            if (addToBackStack) {
                addToBackStack(fragment::class.simpleName)
            }
        }
    }

    private inline fun runOnUiThread(crossinline action: () -> Unit) {
        activity?.runOnUiThread { action() }
    }
/*

    private fun clearBackStack() = runOnUiThread {
        fragmentManager?.popBackStackImmediate(null, POP_BACK_STACK_INCLUSIVE)
    }*/

    override fun onStart() {
        startSplashScreen(SplashScreenFragment.newInstance())
        //replaceFragment(AuthFragment.newInstance(), false)
    }

    override fun onStartMainActivity() {
        replaceFragment(AuthFragment.newInstance(), false)
    }

    //override fun onBackPressed() = currentFragment is MarksFragment

    override fun onFinish(fragment: Fragment) {
        if (currentFragment == fragment) {
            fragmentManager?.popBackStack()
        }
    }

    override fun startHomeLauncher(context: Context) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    private fun startSplashScreen(fragment: Fragment){
        fragmentManager?.transaction {
            replace(R.id.splashScreen, fragment)}
    }

    override fun startGame() {
        replaceFragment(AuthFragment.newInstance(), false)
        fragmentManager?.transaction {
            remove(SplashScreenFragment.newInstance())}
    }

    override fun showGameZone(){
        replaceFragment(GameZoneFragment.newInstance(), false)
    }

    override fun showDecks(){
        replaceFragment(SelectTaskFragment.newInstance(), false)
    }

    override fun showTask() {
        replaceFragment(SelectTaskFragment.newInstance(), false)
    }
}