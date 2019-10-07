package com.wolo.a222.feature.task.view

import android.content.Context
import android.os.Bundle
import android.view.View
import com.wolo.a222.R
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.task.presenter.TaskPresenter
import com.wolo.a222.feature.task.presenter.TaskState
import com.wolo.a222.feature.task.presenter.TaskView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_task.*
import javax.inject.Inject

class TaskFragment : PresenterFragment<TaskPresenter>(), TaskView {

    companion object {
        fun newInstance() = TaskFragment()
    }

    @Inject
    override lateinit var presenter: TaskPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_task

    override fun onAttach(context: Context) {
        injector.getTaskScreen().inject(this)
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

        val doneButtonOnClickListener = View.OnClickListener{
            presenter.doneButtonOnClick()
        }
        doneButton.setOnClickListener(doneButtonOnClickListener)

    }

    private fun handleState(state: TaskState) {
        quest.text = state.task
        theme.text = state.taskTheme
        leftCards.text = state.leftCards
    }
}