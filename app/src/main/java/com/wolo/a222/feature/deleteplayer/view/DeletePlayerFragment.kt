package com.wolo.a222.feature.deleteplayer.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wolo.a222.R
import com.wolo.a222.feature.auth.view.adapter.GamersAdapter
import com.wolo.a222.feature.auth.view.adapter.OnItemCallback
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.deleteplayer.presenter.DeletePlayerPresenter
import com.wolo.a222.feature.deleteplayer.presenter.DeletePlayerState
import com.wolo.a222.feature.deleteplayer.presenter.DeletePlayerView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject

class DeletePlayerFragment : PresenterFragment<DeletePlayerPresenter>(), DeletePlayerView, OnItemCallback {

    companion object {
        fun newInstance() = DeletePlayerFragment()
    }

    @Inject
    override lateinit var presenter: DeletePlayerPresenter
    private var gamersArray = mutableListOf<String>()
    private var adapter = GamersAdapter(gamersArray, this)

    override val layoutResId: Int
        get() = R.layout.fragment_delete_player

    override fun onAttach(context: Context) {
        injector.getDeletePlayerScreen().inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) injector.releaseDeletePlayerScreen()
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

        gamers_lists.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        gamers_lists.adapter = adapter

        val onClickClose = View.OnClickListener {
            presenter.closeDeletePlayer()
        }

        val closeRulesButton = view.findViewById<Button>(R.id.close_delete_player_button)
        closeRulesButton.setOnClickListener(onClickClose)
    }

    private fun handleState(state: DeletePlayerState) {
        gamersArray.clear()
        for (i in state.gamersArray) {
            gamersArray.add(i)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onDeleteItem(item: Int) {
        if (gamersArray.size > 2){
        presenter.deletePlayer(item)}
        else {
            Toast.makeText(context, "В игре должно быть минимум 2 игрока!", Toast.LENGTH_LONG).show()
        }
    }
}