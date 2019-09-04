package com.wolo.a222.View.Activity


import androidx.test.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
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
