package com.wolo.a222.feature.auth.view


import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wolo.a222.R
import com.wolo.a222.feature.auth.presenter.AuthPresenter
import com.wolo.a222.feature.auth.presenter.AuthState
import com.wolo.a222.feature.auth.presenter.AuthView
import com.wolo.a222.feature.auth.view.adapter.GamersAdapter
import com.wolo.a222.feature.auth.view.adapter.OnItemCallback
import com.wolo.a222.feature.common.view.PresenterFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_auth.*
import javax.inject.Inject


class AuthFragment : PresenterFragment<AuthPresenter>(), AuthView, OnItemCallback {

    companion object{
        fun newInstance() = AuthFragment()
    }

    @Inject
    override lateinit var presenter: AuthPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_auth

    private var gamersArray = mutableListOf<String>()
    private var adapter = GamersAdapter(gamersArray, this)

    override fun onAttach(context: Context) {
        injector.getAuthScreen().inject(this)
        super.onAttach(context)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamers_lists.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        gamers_lists.adapter = adapter

        val addPlayerOnClick = View.OnClickListener {
            if (new_user.text.toString() != "") {
                presenter.addNewPlayer(new_user.text.toString())
            }
        }
        add_player.setOnClickListener(addPlayerOnClick)

        val startGameOnClick = View.OnClickListener {
            //gamersArray.reverse()
            when (gamersArray.size) {
                0 -> Toast.makeText(context, "Необходимо добавить минимум 2-х игроков!", Toast.LENGTH_LONG).show()
                1 -> Toast.makeText(context, "Необходимо добавить минимум 1 игрока!", Toast.LENGTH_LONG).show()
                else -> presenter.onClickStartPlay(gamersArray)
            }
        }
        start_game_button.setOnClickListener(startGameOnClick)

        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }
    }

    private fun handleState(state: AuthState) {
        gamersArray.clear()
        for (i in state.gamersArray) {
            gamersArray.add(i)
        }
        adapter.notifyDataSetChanged()
        new_user.setText("")
        if (gamersArray.size == 8 ){
            Toast.makeText(context, "Набрано максимальное количество игроков(8)", Toast.LENGTH_LONG).show()
            add_player.isEnabled = false
            new_user.isEnabled = false
        } else {
            add_player.isEnabled = true
            new_user.isEnabled = true
        }
    }

    override fun onDeleteItem(item: Int) {
        presenter.deletePlayer(item)
    }
}