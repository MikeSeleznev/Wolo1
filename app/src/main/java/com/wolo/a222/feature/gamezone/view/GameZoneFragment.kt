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
import android.widget.ImageView
import android.widget.TextView
import com.wolo.a222.R
import com.wolo.a222.WoloApp.Companion.game
import com.wolo.a222.feature.common.view.PresenterFragment
import com.wolo.a222.feature.gamezone.presenter.GameZonePresenter
import com.wolo.a222.feature.gamezone.presenter.GameZoneState
import com.wolo.a222.feature.gamezone.presenter.GameZoneView
import io.reactivex.android.schedulers.AndroidSchedulers
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

    private var numberOfPlayers = 0
    private var bottle: ImageView? = null
    private var user1: Button? = null
    private var user2: Button? = null
    private var user3: Button? = null
    private var user4: Button? = null
    private var user5: Button? = null
    private var user6: Button? = null
    private var user7: Button? = null
    private var user8: Button? = null
    private var fragmentToSpin: ImageView? = null
    private lateinit var mDetector: GestureDetector
    private var startGamePlayer: TextView? = null

    override val layoutResId: Int
      get() = arguments?.getInt("id", 0) ?: 0
    /*
    init {
        numberOfPlayers = game.numberOfPlayers()
        layoutResId = when (numberOfPlayers) {
            2 -> R.layout.gamezone_two
            3 -> R.layout.gamezone_three
            4 -> R.layout.gamezone_four
            5 -> R.layout.gamezone_five
            6 -> R.layout.gamezone_six
            7 -> R.layout.gamezone_seven
            8 -> R.layout.gamezone_eight
            else -> R.layout.fragment_auth
        }
    }*/

    override fun onAttach(context: Context?) {
        injector.getGameZoneScreen().inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        mDetector = GestureDetector(context, GestureListener())
        val fragmentToSpinOnTouchListener = View.OnTouchListener { _, event -> mDetector.onTouchEvent(event) }
        fragmentToSpin?.setOnTouchListener(fragmentToSpinOnTouchListener)

        presenter.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleState)
                .run { disposeOnDestroyView(this) }
    }

    private fun handleState(state: GameZoneState) {

    }

    private fun init() {

        fragmentToSpin = activity?.findViewById(R.id.fragmentToSpin)
        startGamePlayer = activity?.findViewById(R.id.startGamePlayer)

        var text = ""
        text = if (game.isStartGame!!){
            presenter.whoStartGame()
        }
        else {
            game.whoContinueGame()
        }
        startGamePlayer?.text = text

        bottle = activity?.findViewById(R.id.bottle)
        user1 = activity?.findViewById(R.id.user1)
        user1?.text = game.players[0].shortName
        user2 = activity?.findViewById(R.id.user2)
        user2?.text = game.players[1].shortName

        when (numberOfPlayers) {
            3 -> {
                user3 = activity?.findViewById(R.id.user3)
                user3?.text = game.players[2].shortName
            }
            4 -> {
                user3 = activity?.findViewById(R.id.user3)
                user4 = activity?.findViewById(R.id.user4)

                user3?.text = game.players[2].shortName
                user4?.text = game.players[3].shortName
            }
            5 -> {
                user3 = activity?.findViewById(R.id.user3)
                user4 = activity?.findViewById(R.id.user4)
                user5 = activity?.findViewById(R.id.user5)

                user3?.text = game.players[2].shortName
                user4?.text = game.players[3].shortName
                user5?.text = game.players[4].shortName
            }
            6 -> {
                user3 = activity?.findViewById(R.id.user3)
                user4 = activity?.findViewById(R.id.user4)
                user5 = activity?.findViewById(R.id.user5)
                user6 = activity?.findViewById(R.id.user6)

                user3?.text = game.players[2].shortName
                user4?.text = game.players[3].shortName
                user5?.text = game.players[4].shortName
                user6?.text = game.players[5].shortName
            }
            7 -> {
                user3 = activity?.findViewById(R.id.user3)
                user4 = activity?.findViewById(R.id.user4)
                user5 = activity?.findViewById(R.id.user5)
                user6 = activity?.findViewById(R.id.user6)
                user7 = activity?.findViewById(R.id.user7)

                user3?.text = game.players[2].shortName
                user4?.text = game.players[3].shortName
                user5?.text = game.players[4].shortName
                user6?.text = game.players[5].shortName
                user7?.text = game.players[6].shortName
            }
            else -> {

            }
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
                    1 -> paintGamer(user1, 1)
                    2 -> paintGamer(user2, 2)
                    3 -> paintGamer(user3, 3)
                    4 -> paintGamer(user4, 4)
                    5 -> paintGamer(user5, 5)
                    6 -> paintGamer(user6, 6)
                    7 -> paintGamer(user7, 7)
                    8 -> paintGamer(user8, 28)
                }

                game.setPrevisionsPlayer()

                if (!game.repeatPlayer) {
                    val handler = Handler()
                    handler.postDelayed({
                       presenter.showDecks()
                        //game.selectedPlayer = game.players[selectedUser-1];
                        //val intent = Intent(this@GamezoneActivity, SelectActivity::class.java)
                        //startActivity(intent)
                        //finish()
                    }, 500)
                    game.setNotStartGame()
                } else {
                    startGamePlayer?.text = game.whoRepeat()
                    game.setLastDir()
                }
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        }
        rotation.setAnimationListener(animationListener)
        bottle!!.startAnimation(rotation)
    }

    private fun paintGamer(user: Button?, selectUser: Int) {
        if (user != null) {
            user.isEnabled = false
        }
        var selectedUser = selectUser
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

        when (numberOfPlayers) {
            2 -> {
                user1?.isEnabled = true
                user2?.isEnabled = true
            }
            3 -> {
                user1?.isEnabled = true
                user2?.isEnabled = true
                user3?.isEnabled = true
            }
            4 -> {
                user1?.isEnabled = true
                user2?.isEnabled = true
                user3?.isEnabled = true
                user4?.isEnabled = true
            }
            5 -> {
                user1?.isEnabled = true
                user2?.isEnabled = true
                user3?.isEnabled = true
                user4?.isEnabled = true
                user5?.isEnabled = true
            }
            6 -> {
                user1?.isEnabled = true
                user2?.isEnabled = true
                user3?.isEnabled = true
                user4?.isEnabled = true
                user5?.isEnabled = true
                user6?.isEnabled = true
            }
            7 -> {
                user1?.isEnabled = true
                user2?.isEnabled = true
                user3?.isEnabled = true
                user4?.isEnabled = true
                user5?.isEnabled = true
                user6?.isEnabled = true
                user7?.isEnabled = true
            }
            else -> {
                user1?.isEnabled = true
                user2?.isEnabled = true
                user3?.isEnabled = true
                user4?.isEnabled = true
                user5?.isEnabled = true
                user6?.isEnabled = true
                user7?.isEnabled = true
                user8?.isEnabled = true

            }
        }
    }
}