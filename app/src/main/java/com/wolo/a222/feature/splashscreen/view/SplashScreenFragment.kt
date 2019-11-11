package com.wolo.a222.feature.splashscreen.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.MainActivity
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.splashscreen.presenter.SplashScreenPresenter
import com.wolo.a222.feature.splashscreen.presenter.SplashScreenState
import com.wolo.a222.feature.splashscreen.presenter.SplashScreenView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_splash_screen.*
import javax.inject.Inject

class SplashScreenFragment : PresenterFragment<SplashScreenPresenter>(), SplashScreenView {

    companion object{
        fun newInstance() = SplashScreenFragment()
    }

    @Inject
    override lateinit var  presenter: SplashScreenPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_splash_screen

    override fun onAttach(context: Context) {
        injector.getSplashScreenScreen().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }
    }

    private fun handleState(state: SplashScreenState) {

        if (state.dateIsLoaded) {
            loadingText.text = resources.getString(R.string.data_is_loaded)
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }else {
            loadingText.text = resources.getString(R.string.text_loading)
        }
    }
}