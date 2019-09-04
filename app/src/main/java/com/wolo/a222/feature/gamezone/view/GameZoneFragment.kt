package com.wolo.a222.feature.gamezone.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
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
        fun newInstance() = GameZoneFragment()
    }

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

    override var layoutResId: Int = 0
    //get() = R.layout.fragment_auth

    init {
        numberOfPlayers = game.numberOfPlayers()
        layoutResId = when (game.numberOfPlayers()) {
            2 -> R.layout.gamezone_two
            3 -> R.layout.gamezone_three
            4 -> R.layout.gamezone_four
            5 -> R.layout.gamezone_five
            6 -> R.layout.gamezone_six
            7 -> R.layout.gamezone_seven
            8 -> R.layout.gamezone_eight
            else -> R.layout.fragment_auth
        }
    }


    override fun onAttach(context: Context?) {
        injector.getGameZoneScreen().inject(this)
        super.onAttach(context)
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

        fragmentToSpin = activity?.findViewById<ImageView>(R.id.fragmentToSpin)
        startGamePlayer = activity?.findViewById<TextView>(R.id.startGamePlayer)

        bottle = activity?.findViewById<ImageView>(R.id.bottle)
        user1 = activity?.findViewById<Button>(R.id.user1)
        user2 = activity?.findViewById<Button>(R.id.user2)

        when (numberOfPlayers) {
            3 -> user3 = activity?.findViewById<Button>(R.id.user3)
            4 -> {
                user3 = activity?.findViewById<Button>(R.id.user3)
                user4 = activity?.findViewById<Button>(R.id.user4)
            }
            5 -> {
                user3 = activity?.findViewById<Button>(R.id.user3)
                user4 = activity?.findViewById<Button>(R.id.user4)
                user5 = activity?.findViewById<Button>(R.id.user5)
            }
            6 -> {
                user3 = activity?.findViewById<Button>(R.id.user3)
                user4 = activity?.findViewById<Button>(R.id.user4)
                user5 = activity?.findViewById<Button>(R.id.user5)
                user6 = activity?.findViewById<Button>(R.id.user6)
            }
            7 -> {
                user3 = activity?.findViewById<Button>(R.id.user3)
                user4 = activity?.findViewById<Button>(R.id.user4)
                user5 = activity?.findViewById<Button>(R.id.user5)
                user6 = activity?.findViewById<Button>(R.id.user6)
                user7 = activity?.findViewById<Button>(R.id.user7)
            }
            else -> {

            }
        }
    }

    fun startGame() {
        //setBackgroundColor()
        game.startOnePlay()
        val pointWidth = (bottle!!.width / 2).toFloat()
        val pointHeight = (bottle!!.height / 2).toFloat()
        val rotation = RotateAnimation(game.last_dir, game.new_dir, pointWidth, pointHeight)
        rotation.duration = 2700
        rotation.fillAfter = true
        val animationListener = object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                when (game.numberChoosedPlayer) {
                    1 -> paintGamer(user1, 1)
                    2 -> paintGamer(user2, 2)
                    3 -> paintGamer(user3, 3)
                    4 -> paintGamer(user4, 4)
                    5 -> paintGamer(user5, 5)
                    6 -> paintGamer(user6, 6)
                    7 -> paintGamer(user7, 7)
                    8 -> paintGamer(user8, 28)
                }

                game.setPrevisiousPlayer()

                if (!game.repeatPlayer) {
                    val handler = Handler()
                    handler.postDelayed({
                       presenter.showDecks()
                        //game.setSelectedPlayer(game.players[selectedUser-1]);
                        //val intent = Intent(this@GamezoneActivity, SelectActivity::class.java)
                        //startActivity(intent)
                        //finish()
                    }, 500)
                    game.setNotStartGame()
                } else {
                    startGamePlayer?.text = game.whoRepeat()
                    game.setLast_dir()
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
            val image = resources.getDrawable(R.drawable.circleactive) as Drawable
            user.background = image
            user.setTextColor(Color.WHITE)
        }
        //selectedUser = selectUser
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


}