package com.wolo.a222.feature.common.view

import android.os.Bundle
import android.view.View
import com.wolo.a222.feature.common.presenter.Presenter

abstract class PresenterFragment<T : Presenter<*>> : InjectableFragment() {

    protected abstract val presenter: T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*presenter.notificationsBus()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it.isActual = false
                showNotification(it)
            }
            .run { disposeOnDestroyView(this) }*/
    }

    override fun onPause() {
        super.onPause()
        if (requireActivity().isFinishing) {
            presenter.onFinish()
        }
    }

}
