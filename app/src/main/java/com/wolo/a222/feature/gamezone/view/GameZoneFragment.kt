package com.wolo.a222.feature.gamezone.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.Button
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.gamezone.presenter.GameZonePresenter
import com.wolo.a222.feature.gamezone.presenter.GameZoneState
import com.wolo.a222.feature.gamezone.presenter.GameZoneView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.gamezone_eight.*
import kotlinx.android.synthetic.main.gamezone_three.user3
import kotlinx.android.synthetic.main.gamezone_two.bottle
import kotlinx.android.synthetic.main.gamezone_two.fragmentToSpin
import kotlinx.android.synthetic.main.gamezone_two.startGamePlayer
import kotlinx.android.synthetic.main.gamezone_two.user1
import kotlinx.android.synthetic.main.gamezone_two.user2
import javax.inject.Inject

class GameZoneFragment : PresenterFragment<GameZonePresenter>(), GameZoneView {

    companion object {
        fun newInstance(id: Int) = GameZoneFragment().apply {
            arguments = Bundle().apply {
                putInt("id", id)
            }
    }}

    @Inject
    override lateinit var presenter: GameZonePresenter

    private lateinit var mDetector: GestureDetector
    private var gamersIcons = mutableListOf<Button>()


    override val layoutResId: Int
      get() = arguments?.getInt("id", 0) ?: 0


    override fun onAttach(context: Context) {
        injector.getGameZoneScreen().inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Keyboard().hideKeyboard(activity!!.applicationContext)

        init()

        //bottle.rotation = game.lastDir

        mDetector = GestureDetector(context, GestureListener())
        val fragmentToSpinOnTouchListener = View.OnTouchListener { _, event ->
            mDetector.onTouchEvent(event) }
        fragmentToSpin.setOnTouchListener(fragmentToSpinOnTouchListener)

        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }
    }

    private fun handleState(state: GameZoneState) {
        startGamePlayer.text = state.startGamePlayer
    }

    private fun init() {

        presenter.whoTurn()

        gamersIcons.add(user1)
        gamersIcons.add(user2)

        when (presenter.numberOfPlayers()) {
            3 -> {
                gamersIcons.add(user3)
            }
            4 -> {
                gamersIcons.add(user3)
                gamersIcons.add(user4)
            }
            5 -> {
                gamersIcons.add(user3)
                gamersIcons.add(user4)
                gamersIcons.add(user5)
            }
            6 -> {
                gamersIcons.add(user3)
                gamersIcons.add(user4)
                gamersIcons.add(user5)
                gamersIcons.add(user6)
            }
            7 -> {
                gamersIcons.add(user3)
                gamersIcons.add(user4)
                gamersIcons.add(user5)
                gamersIcons.add(user6)
                gamersIcons.add(user7)
            }
            8 -> {
                gamersIcons.add(user3)
                gamersIcons.add(user4)
                gamersIcons.add(user5)
                gamersIcons.add(user6)
                gamersIcons.add(user7)
                gamersIcons.add(user8)
            }
            else -> {

            }
        }

        gamersIcons.forEachIndexed { index, element ->
            element.text = game.players[index].shortName
        }
    }

    fun startGame() {
        setBackgroundColor()
        startGamePlayer?.text = ""
        presenter.startOnePlay()
        val pointWidth = (bottle!!.width / 2).toFloat()
        val pointHeight = (bottle!!.height / 2).toFloat()
        val rotation = RotateAnimation(game.lastDir, game.newDir, pointWidth, pointHeight)
        rotation.duration = 2700
        rotation.fillAfter = true
        val animationListener = object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                when (presenter.numberChoosedPlayer()) {
                    1 -> paintGamer(user1)
                    2 -> paintGamer(user2)
                    3 -> paintGamer(user3)
                    4 -> paintGamer(user4)
                    5 -> paintGamer(user5)
                    6 -> paintGamer(user6)
                    7 -> paintGamer(user7)
                    8 -> paintGamer(user8)
                }

                game.setPrevisionsPlayer()

                if (!game.repeatPlayer) {
                    val handler = Handler()
                    handler.postDelayed({
                       presenter.showDecks()
                    }, 500)
                } else {
                    startGamePlayer.text = game.whoRepeat()
                    game.setLastDir()
                }
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        }
        rotation.setAnimationListener(animationListener)
        bottle!!.startAnimation(rotation)
    }

    private fun paintGamer(user: Button?) {
        if (user != null) {
            user.isEnabled = false
        }
    }


    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return super.onSingleTapUp(e)
        }

        override fun onDown(e: MotionEvent?): Boolean {
            //startGame()
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            return super.onFling(e1, e2, velocityX, velocityY)
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            return super.onDoubleTap(e)
        }

        override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
            Log.i("TAP", "onScroll: ")
            if (distanceY > 0 || distanceX > 0) {
                startGame()
            }
            return true
        }

        override fun onContextClick(e: MotionEvent?): Boolean {
            return super.onContextClick(e)
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            Log.i("TAP", "onSingleTapConfirmed: ")
            startGame()
            return true
        }

        override fun onShowPress(e: MotionEvent?) {
            super.onShowPress(e)
        }

        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return super.onDoubleTapEvent(e)
        }

        override fun onLongPress(e: MotionEvent?) {
            super.onLongPress(e)
        }
    }

    private fun setBackgroundColor() {
        gamersIcons.forEach {
            it.isEnabled = true
        }
    }
}