package com.wolo.a222.feature.selecttask.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.selecttask.presenter.SelectTaskPresenter
import com.wolo.a222.feature.selecttask.presenter.SelectTaskState
import com.wolo.a222.feature.selecttask.presenter.SelectTaskVM
import com.wolo.a222.feature.selecttask.presenter.SelectTaskView
import com.wolo.a222.feature.selecttask.view.adapter.OnClickItemSelectTaskCallback
import com.wolo.a222.feature.selecttask.view.adapter.SelectTaskAdapter
import com.wolo.a222.feature.selecttask.view.adapter.SelectTaskDelegate
import com.wolo.a222.model.sku.SkuDeck
import com.wolo.a222.utils.Keyboard
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_select_task.*
import javax.inject.Inject

class SelectTaskFragment : PresenterFragment<SelectTaskPresenter>(), SelectTaskView, OnClickItemSelectTaskCallback {

    companion object {
        fun newInstance() = SelectTaskFragment()
    }

    @Inject
    override lateinit var presenter: SelectTaskPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_select_task

    private val adapter: SelectTaskAdapter by lazy {
        SelectTaskAdapter().also {
            it.addDelegate(SelectTaskDelegate(this))
        }
    }

    override fun onAttach(context: Context) {
        injector.getSelectTaskScreen().inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) injector.releaseSelectTaskScreen()
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
        injector.getSelectTaskScreen().inject(this)

        presenter.getPacks()
        selectedUser.text = game.choosedPlayer!!.fullName

        packsRecycle.layoutManager = GridLayoutManager(requireContext(), 2)
        packsRecycle.adapter = adapter
        packsRecycle.setHasFixedSize(true)
            }

    override fun onStart() {
        super.onStart()
        presenter.setIntLeftCards()
    }

    private fun handleState(state: SelectTaskState) {
        adapter.items = state.taskList
    }

    override fun onClickItem(item: SelectTaskVM) {
        presenter.showTask(item)
    }
}