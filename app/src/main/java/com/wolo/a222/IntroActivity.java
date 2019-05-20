package com.wolo.a222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wolo.a222.Firebase.InitFB;

public class IntroActivity extends AppCompatActivity {

    TextView loadingText;
    Game game;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        loadingText = findViewById(R.id.loadingText);
        game = new Game();

        Gson gson = new Gson();
        String json = gson.toJson(game);
        sPref = PreferenceManager.getDefaultSharedPreferences(IntroActivity.this);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("game", json);
        ed.commit();

        new InitFB(this);

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 800);*/
    }
}
