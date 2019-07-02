package com.wolo.a222.View.Activity


import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.wolo.a222.Presenter.FirebasePresenter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class IntroActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(IntroActivity::class.java)

    @Test
    fun introActivityTest() {
        var appContext = InstrumentationRegistry.getContext()
        var fireBase =  FirebasePresenter()


        assert(fireBase.hasConnection(appContext))
    }
}
