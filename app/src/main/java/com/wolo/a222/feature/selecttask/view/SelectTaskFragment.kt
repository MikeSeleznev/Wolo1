package com.wolo.a222.feature.selecttask.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.selecttask.presenter.SelectTaskPresenter
import com.wolo.a222.feature.selecttask.presenter.SelectTaskState
import com.wolo.a222.feature.selecttask.presenter.SelectTaskView
import com.wolo.a222.feature.selecttask.view.adapter.SelectTaskAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_select_task.*
import kotlinx.android.synthetic.main.fragment_select_task.grid_view
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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }

        presenter.getPacks()

        selectedUser.text = game.choosedPlayer!!.fullName

    }

    override fun onStart() {
        super.onStart()
        presenter.setIntLeftCards()
    }

    private fun handleState(state: SelectTaskState) {
        grid_view.adapter = SelectTaskAdapter(activity!!.applicationContext, state.taskList)

        grid_view.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->
            presenter.showTask(state.taskList[i].id)
        }
    }
}