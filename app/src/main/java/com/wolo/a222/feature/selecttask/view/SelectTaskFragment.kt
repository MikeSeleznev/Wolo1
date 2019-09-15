package com.wolo.a222.feature.selecttask.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import android.widget.ImageButton
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

    lateinit var numCardsUsuall: CheckedTextView
    lateinit var numCardsExtreme: CheckedTextView
    lateinit var kolodaNumCards3: CheckedTextView
    lateinit var kolodaNumCards4: CheckedTextView
    lateinit var kolodaNumCards5: CheckedTextView
    lateinit var usual: ImageButton

    override val layoutResId: Int
        get() = R.layout.fragment_select_task

    override fun onAttach(context: Context?) {
        injector.getSelectTaskScreen().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numCardsUsuall = view.findViewById(R.id.numCardsUsuall)
        numCardsExtreme = view.findViewById(R.id.numCardsExtreme)
        kolodaNumCards3 = view.findViewById(R.id.kolodanumcards3)
        kolodaNumCards4 = view.findViewById(R.id.kolodanumcards5)

        usual = view.findViewById(R.id.usual)

        var usuallClickListener = View.OnClickListener {
            presenter.showTask(Const.USUAL)
        }
        usual.setOnClickListener(usuallClickListener)

        var numCardsExtremeClickListener = View.OnClickListener {
            var a = "a"
        }
        numCardsExtreme.setOnClickListener(numCardsExtremeClickListener)
        selectedUser.text = game.choosedPlayer.fullName
        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }
    }

    override fun onStart() {
        super.onStart()
        presenter.setIntLeftCards()
    }

    private fun handleState(state: SelectTaskState) {
        numCardsUsuall.text = state.selectTask?.kolodaNumCards1
        numCardsExtreme.text = state.selectTask?.kolodaNumCards2
        kolodaNumCards3.text = state.selectTask?.kolodaNumCards3
        kolodaNumCards4.text = state.selectTask?.kolodaNumCards4
        //kolodaNumCards5.text = state.selectTask?.kolodaNumCards5
    }
}