package com.wolo.a222.feature.rules.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.rules.presenter.RulesPresenter
import com.wolo.a222.feature.rules.presenter.RulesState
import com.wolo.a222.feature.rules.presenter.RulesView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class RulesFragment : PresenterFragment<RulesPresenter>(), RulesView{

    companion object {
        fun newInstance() = RulesFragment()
    }

    @Inject
    override lateinit var presenter: RulesPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_rules

    override fun onAttach(context: Context) {
        injector.getRulesScreen().inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) injector.releaseRulesScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onClickClose = View.OnClickListener {
            presenter.closeRules()
        }
        val closeRulesButton = view.findViewById<Button>(R.id.close_rules_button)
        closeRulesButton.setOnClickListener(onClickClose)
    }

    private fun handleState(state: RulesState) {

    }

}