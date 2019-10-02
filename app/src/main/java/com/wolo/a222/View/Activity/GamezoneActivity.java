package com.wolo.a222.View.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.wolo.a222.Players;
import com.wolo.a222.R;
import com.wolo.a222.SelectActivity;
import com.wolo.a222.feature.common.model.Game;

public class GamezoneActivity extends AppCompatActivity {

    private ImageView bottle;
    private Button user1;
    private Button user2;
    private Button user3;
    private Button user4;
    private Button user5;
    private Button user6;
    private Button user7;
    private Button user8;
    private int numberOfPlayers;
    private int selectedUser = 0;
    private Game game;
    private SharedPreferences sPref;
    private Boolean openFragment;
    private TextView startGamePlayer;
    private String textQueue;
    private GestureDetector mDetector;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetector = new GestureDetector(this, new MyGestureListener());
        openFragment = false;

        Gson gson = new Gson();
        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "");
        game = gson.fromJson(json, Game.class);

        numberOfPlayers = game.numberOfPlayers();


        if (numberOfPlayers == 2){
            setContentView(R.layout.gamezone_two);
            user1 = findViewById(R.id.user1);
            user2 = findViewById(R.id.user2);

        } else if(numberOfPlayers == 3){
            setContentView(R.layout.gamezone_three);
            user1 = findViewById(R.id.user1);
            user2 = findViewById(R.id.user2);
            user3 = findViewById(R.id.user3);
        }else if(numberOfPlayers == 4){
            setContentView(R.layout.gamezone_four);
            user1 = findViewById(R.id.user1);
            user2 = findViewById(R.id.user2);
            user3 = findViewById(R.id.user3);
            user4 = findViewById(R.id.user4);}
        else if(numberOfPlayers == 5){
            setContentView(R.layout.gamezone_five);
            user1 = findViewById(R.id.user1);
            user2 = findViewById(R.id.user2);
            user3 = findViewById(R.id.user3);
            user4 = findViewById(R.id.user4);
            user5 = findViewById(R.id.user5);}
        else if(numberOfPlayers == 6){
            setContentView(R.layout.gamezone_six);
            user1 = findViewById(R.id.user1);
            user2 = findViewById(R.id.user2);
            user3 = findViewById(R.id.user3);
            user4 = findViewById(R.id.user4);
            user5 = findViewById(R.id.user5);
            user6 = findViewById(R.id.user6);}
        else if(numberOfPlayers == 7){
            setContentView(R.layout.gamezone_seven);
            user1 = findViewById(R.id.user1);
            user2 = findViewById(R.id.user2);
            user3 = findViewById(R.id.user3);
            user4 = findViewById(R.id.user4);
            user5 = findViewById(R.id.user5);
            user6 = findViewById(R.id.user6);
            user7 = findViewById(R.id.user7);}
        else{
            setContentView(R.layout.gamezone_eight);
            user1 = findViewById(R.id.user1);
            user2 = findViewById(R.id.user2);
            user3 = findViewById(R.id.user3);
            user4 = findViewById(R.id.user4);
            user5 = findViewById(R.id.user5);
            user6 = findViewById(R.id.user6);
            user7 = findViewById(R.id.user7);
            user8 = findViewById(R.id.user8);}

        bottle = findViewById(R.id.bottle);
        //bottle.setRotation(game.getDegree());

        startGamePlayer = (TextView) findViewById(R.id.startGamePlayer);
        startGamePlayer.setVisibility(View.INVISIBLE);

        game.setLastDir(0f);

        if (game.isStartGame() == true) {
            textQueue = game.whoStartGame(); }
            else{
            textQueue = game.whoContinueGame();
            }
        startGamePlayer.setText(textQueue);
        startGamePlayer.setVisibility(View.VISIBLE);



        setVisibleGamers(game.players);

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // pass the events to the gesture detector
                // a return value of true means the detector is handling it
                // a return value of false means the detector didn't
                // recognize the event
                return mDetector.onTouchEvent(event);

            }
        };
        ImageView fragmentToSpin = (ImageView) findViewById(R.id.fragmentToSpin);
        fragmentToSpin.setOnTouchListener(touchListener);



        String jsonT = gson.toJson(game);
        sPref = PreferenceManager.getDefaultSharedPreferences(GamezoneActivity.this);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("game", jsonT);
        ed.commit();


    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d("TAG","onDown: ");

            // don't return false here or else none of the other
            // gestures will work
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.i("TAG", "onSingleTapConfirmed: ");
            startGame();
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.i("TAG", "onDoubleTap: ");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            Log.i("TAG", "onScroll: ");
            if(distanceY>0 || distanceX>0){
                startGame();
            }
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d("TAG", "onFling: ");
            //startGame();
            return true;
        }
    }



    private void setVisibleGamers(Players[] players) {

            user1.setText(players[0].getShortName());
            user2.setText(players[1].getShortName());
        if (numberOfPlayers == 3) {
            user3.setText(players[2].getShortName());
        } else if (numberOfPlayers == 4) {
            user3.setText(players[2].getShortName());
            user4.setText(players[3].getShortName());
        } else if (numberOfPlayers == 5) {
            user3.setText(players[2].getShortName());
            user4.setText(players[3].getShortName());
            user5.setText(players[4].getShortName());
        } else if (numberOfPlayers == 6) {
            user3.setText(players[2].getShortName());
            user4.setText(players[3].getShortName());
            user5.setText(players[4].getShortName());
            user6.setText(players[5].getShortName());
        } else if (numberOfPlayers == 7) {
            user3.setText(players[2].getShortName());
            user4.setText(players[3].getShortName());
            user5.setText(players[4].getShortName());
            user6.setText(players[5].getShortName());
            user7.setText(players[6].getShortName());
        } else if (numberOfPlayers == 8){
            user3.setText(players[2].getShortName());
            user4.setText(players[3].getShortName());
            user5.setText(players[4].getShortName());
            user6.setText(players[5].getShortName());
            user7.setText(players[6].getShortName());
            user8.setText(players[7].getShortName());
        }
    }

    private void paintGamer(Button user, int selectUser) {
        if (!(user == null)){
        Drawable image = (Drawable) getResources().getDrawable(R.drawable.circleactive);
        user.setBackground(image);
        user.setTextColor(Color.WHITE);
        }
        selectedUser = selectUser;
    }

    private void setBackgroundColor() {

        Drawable image = (Drawable) getResources().getDrawable(R.drawable.circle);
        if (numberOfPlayers == 2){
            user1.setBackground(image);
            user1.setTextColor(Color.BLACK);

            user2.setBackground(image);
            user2.setTextColor(Color.BLACK);
        } else if(numberOfPlayers == 3){
            user1.setBackground(image);
            user1.setTextColor(Color.BLACK);

            user2.setBackground(image);
            user2.setTextColor(Color.BLACK);

            user3.setBackground(image);
            user3.setTextColor(Color.BLACK);
        }else if(numberOfPlayers == 4){
            user1.setBackground(image);
            user1.setTextColor(Color.BLACK);

            user2.setBackground(image);
            user2.setTextColor(Color.BLACK);

            user3.setBackground(image);
            user3.setTextColor(Color.BLACK);

            user4.setBackground(image);
            user4.setTextColor(Color.BLACK);
        }else if(numberOfPlayers == 5){
            user1.setBackground(image);
            user1.setTextColor(Color.BLACK);

            user2.setBackground(image);
            user2.setTextColor(Color.BLACK);

            user3.setBackground(image);
            user3.setTextColor(Color.BLACK);

            user4.setBackground(image);
            user4.setTextColor(Color.BLACK);

            user5.setBackground(image);
            user5.setTextColor(Color.BLACK);
        }else if(numberOfPlayers == 6){
            user1.setBackground(image);
            user1.setTextColor(Color.BLACK);

            user2.setBackground(image);
            user2.setTextColor(Color.BLACK);

            user3.setBackground(image);
            user3.setTextColor(Color.BLACK);

            user4.setBackground(image);
            user4.setTextColor(Color.BLACK);

            user5.setBackground(image);
            user5.setTextColor(Color.BLACK);

            user6.setBackground(image);
            user6.setTextColor(Color.BLACK);}
        else if(numberOfPlayers == 7){
            user1.setBackground(image);
            user1.setTextColor(Color.BLACK);

            user2.setBackground(image);
            user2.setTextColor(Color.BLACK);

            user3.setBackground(image);
            user3.setTextColor(Color.BLACK);

            user4.setBackground(image);
            user4.setTextColor(Color.BLACK);

            user5.setBackground(image);
            user5.setTextColor(Color.BLACK);

            user6.setBackground(image);
            user6.setTextColor(Color.BLACK);

            user7.setBackground(image);
            user7.setTextColor(Color.BLACK);}
        else{

        user1.setBackground(image);
        user1.setTextColor(Color.BLACK);

        user2.setBackground(image);
        user2.setTextColor(Color.BLACK);

        user3.setBackground(image);
        user3.setTextColor(Color.BLACK);

        user4.setBackground(image);
        user4.setTextColor(Color.BLACK);

        user5.setBackground(image);
        user5.setTextColor(Color.BLACK);

        user6.setBackground(image);
        user6.setTextColor(Color.BLACK);

        user7.setBackground(image);
        user7.setTextColor(Color.BLACK);

        user8.setBackground(image);
        user8.setTextColor(Color.BLACK);}
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
         super.onResume();

        //    bottle.setRotation(game.getNewDir());

    }

    private void startGame(){
        setBackgroundColor();
        game.startOnePlay();
        float pointWidth = bottle.getWidth() / 2;
        float pointHeight = bottle.getHeight() / 2;
        final Animation rotation = new RotateAnimation(game.getLastDir(), game.getNewDir(), pointWidth, pointHeight);
        rotation.setDuration(2700);
        rotation.setFillAfter(true);
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                        /*if(game.getRepeatPlayer()){
                            rotationplus();
                        }
                        else {*/
                int choosedP = game.getNumberChoosedPlayer();
                if (choosedP == 1) {
                    paintGamer(user1, 1);
                } else if (choosedP == 2) {
                    paintGamer(user2, 2);
                } else if (choosedP == 3) {
                    paintGamer(user3, 3);
                } else if (choosedP == 4) {
                    paintGamer(user4, 4);
                } else if (choosedP == 5) {
                    paintGamer(user5, 5);
                } else if (choosedP == 6) {
                    paintGamer(user6, 6);
                } else if (choosedP == 7) {
                    paintGamer(user7, 7);
                } else if (choosedP == 8) {
                    paintGamer(user8, 28);
                }

                //game.setLastDir();
                game.setPrevisionsPlayer();

                if (!game.getRepeatPlayer()==true) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //game.setSelectedPlayer(game.players[selectedUser-1]);

                            Gson gson = new Gson();
                            String json = gson.toJson(game);
                            sPref = PreferenceManager.getDefaultSharedPreferences(GamezoneActivity.this);
                            SharedPreferences.Editor ed = sPref.edit();
                            ed.putString("game", json);
                            ed.commit();

                            Intent intent = new Intent(GamezoneActivity.this, SelectActivity.class);

                            //intent.putExtra("user", game.getSelectedPlayer().getFullName());
                            startActivity(intent);
                            finish();
                        }
                    }, 500);
                    game.setNotStartGame();
                }
                else{
                    textQueue = game.whoRepeat();
                    startGamePlayer.setText(textQueue);
                    game.setLastDir();
                }
            }
            //}


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bottle.startAnimation(rotation);
    }

    @Override
    protected void onStart() {
        super.onStart();
       Gson gson = new Gson();
        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "");
        game = gson.fromJson(json, Game.class);

        numberOfPlayers = game.numberOfPlayers();


    }
}

