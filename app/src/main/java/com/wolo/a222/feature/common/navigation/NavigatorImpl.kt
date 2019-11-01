package com.wolo.a222.feature.common.navigation


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction
import com.wolo.a222.R
import com.wolo.a222.feature.auth.view.AuthFragment
import com.wolo.a222.feature.common.di.Scope.PerApplication
import com.wolo.a222.feature.deleteplayer.view.DeletePlayerFragment
import com.wolo.a222.feature.gamezone.view.GameZoneFragment
import com.wolo.a222.feature.rules.view.RulesFragment
import com.wolo.a222.feature.selecttask.view.SelectTaskFragment
import com.wolo.a222.feature.shop.view.ShopFragment
import com.wolo.a222.feature.splashscreen.view.SplashScreenFragment
import com.wolo.a222.feature.task.view.TaskFragment
import javax.inject.Inject


@PerApplication
class NavigatorImpl
    @Inject constructor(): Navigator {

    private var fragment: Fragment? = null

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
        replaceFragment(AuthFragment.newInstance(), true)
        fragmentManager?.transaction {
            remove(SplashScreenFragment.newInstance())}
    }

    override fun showGameZone(id: Int){
        replaceFragment(GameZoneFragment.newInstance(id), true)
    }

    override fun showDecks(){
        replaceFragment(SelectTaskFragment.newInstance(), true)
    }

    override fun showSelectTask() {
        replaceFragment(SelectTaskFragment.newInstance(), true)
    }

    override fun showTask() {
        replaceFragment(TaskFragment.newInstance(), true)
    }

    override fun doneTask(id: Int){
        //fragmentManager?.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentManager?.popBackStack()
        showGameZone(id)
    }

    override fun showShop() {
        replaceFragment(ShopFragment.newInstance(), true)
    }

    override fun closeShop(){
        fragmentManager?.popBackStack()
    }

    override fun showRules() {
        replaceFragment(RulesFragment.newInstance(), true)
    }

    override fun closeRules(){
        fragmentManager?.popBackStack()
    }

    override fun showDeletePlayer() {
        replaceFragment(DeletePlayerFragment.newInstance(), true)
    }

    override fun closeDeletePlayer() {
        fragmentManager?.popBackStack()
    }
}