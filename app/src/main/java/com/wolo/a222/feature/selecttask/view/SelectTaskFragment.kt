package com.wolo.a222.feature.selecttask.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.wolo.a222.Const
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.selecttask.presenter.SelectTaskPresenter
import com.wolo.a222.feature.selecttask.presenter.SelectTaskState
import com.wolo.a222.feature.selecttask.presenter.SelectTaskView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_select_task.*
import javax.inject.Inject

class SelectTaskFragment : PresenterFragment<SelectTaskPresenter>(), SelectTaskView{

    companion object {
        fun newInstance() = SelectTaskFragment()
    }

    @Inject
    override lateinit var presenter: SelectTaskPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_select_task

    override fun onAttach(context: Context) {
        injector.getSelectTaskScreen().inject(this)
        super.onAttach(context)
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

        val usualClickListener = View.OnClickListener {
            presenter.showTask(Const.USUAL)
        }
        usual.setOnClickListener(usualClickListener)

        val numCardsExtremeClickListener = View.OnClickListener {
            presenter.showTask(Const.EXTREME)
        }
        extreme.setOnClickListener(numCardsExtremeClickListener)

        selectedUser.text = game.choosedPlayer!!.fullName

    }

    override fun onStart() {
        super.onStart()
        presenter.setIntLeftCards()
    }

    private fun handleState(state: SelectTaskState) {
        numCardsUsuall.text = state.selectTask?.kolodaNumCards5
        numCardsExtreme.text = state.selectTask?.kolodaNumCards2
        numCardsSport.text = state.selectTask?.kolodaNumCards4
        numCardsOhFuck.text = state.selectTask?.kolodaNumCards3
        numCardsErotic.text = state.selectTask?.kolodaNumCards1
    }
}