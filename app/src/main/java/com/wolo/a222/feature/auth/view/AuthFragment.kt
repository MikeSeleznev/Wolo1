package com.wolo.a222.feature.auth.view


import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wolo.a222.R
import com.wolo.a222.feature.auth.presenter.AuthPresenter
import com.wolo.a222.feature.auth.presenter.AuthState
import com.wolo.a222.feature.auth.presenter.AuthView
import com.wolo.a222.feature.auth.view.Adapter.GamersAdapter
import com.wolo.a222.feature.common.view.PresenterFragment
import javax.inject.Inject
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : PresenterFragment<AuthPresenter>(), AuthView {

    companion object{
        fun newInstance() = AuthFragment()
    }

    @Inject
    override lateinit var presenter: AuthPresenter

    override val layoutResId: Int
        get() = R.layout.fragment_auth

    private var gamersArray = mutableListOf<String>()
    private var adapter = GamersAdapter(gamersArray)

    override fun onAttach(context: Context?) {
        injector.getAuthScreen().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamers_lists.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        gamers_lists.adapter = adapter

        val addPlayerOnClick = View.OnClickListener {
           if (new_user.text.toString() !== "")
            gamersArray.add(new_user.text.toString())
            adapter.notifyDataSetChanged()
            new_user.setText("")
        }
        add_player.setOnClickListener(addPlayerOnClick)

        val startGameOnClick = View.OnClickListener {
            presenter.onClickStartPlay(gamersArray)
        }
        start_game_button.setOnClickListener(startGameOnClick)

        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }
    }

    private fun handleState(state: AuthState) {

    }
}