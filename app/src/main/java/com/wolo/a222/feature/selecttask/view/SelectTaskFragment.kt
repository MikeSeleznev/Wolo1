package com.wolo.a222.feature.selecttask.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckedTextView
import android.widget.TextView
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.selecttask.presenter.SelectTaskPresenter
import com.wolo.a222.feature.selecttask.presenter.SelectTaskState
import com.wolo.a222.feature.selecttask.presenter.SelectTaskView
import io.reactivex.android.schedulers.AndroidSchedulers
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

        var numCardsUsuallClickListener = View.OnClickListener {

        }
        numCardsUsuall.setOnClickListener(numCardsUsuallClickListener)

        var numCardsExtremeClickListener = View.OnClickListener {

        }
        numCardsExtreme.setOnClickListener(numCardsExtremeClickListener)

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