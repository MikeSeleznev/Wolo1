package com.wolo.a222;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;

public class TaskActivity extends AppCompatActivity {
    TextView theme;
    TextView quest;
    SharedPreferences sPref;
    TextView leftCards;
    Button doneButton;
    Game game;
    String pack;
    ImageButton closeMenuImageButton;
    ImageButton topMenu;
    Boolean openFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        openFragment = false;
        theme = findViewById(R.id.theme);
        quest = findViewById(R.id.quest);
        leftCards = findViewById(R.id.leftCards);
        doneButton = findViewById(R.id.doneButton);

        topMenu = (ImageButton) findViewById(R.id.topmenuTaskActivity);
        closeMenuImageButton = (ImageButton) findViewById(R.id.closeMenuImageButtonTaskActivity);
        closeMenuImageButton.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        pack = intent.getStringExtra("pack");

        Gson gson = new Gson();
        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "");
        game = gson.fromJson(json, Game.class);
        game.minusOneCard(pack);


        leftCards.setText(game.leftCardsText(pack));
        theme.setText(game.getNamePack(pack));
        quest.setText(game.getRandomQuestion(pack));
        //usuall.cards.remove(question);


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                String json = gson.toJson(game);
                sPref = PreferenceManager.getDefaultSharedPreferences(TaskActivity.this);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("game", json);
                ed.commit();

                Intent intent = new Intent(TaskActivity.this, GamezoneActivity.class);
                startActivity(intent);
                finish();
            }
        });

        topMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,TopMenuActivity.class);
                // startActivity(intent);
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment);
                if (openFragment == false){
                    TopMenuActivity frag = new TopMenuActivity();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    //ft.replace(R.id.fragment, frag);
                    ft.add(R.id.fragment, frag);
                    ft.addToBackStack(null);
                    ft.commit();
                    openFragment = true;
                    topMenu.setVisibility(View.INVISIBLE);
                    closeMenuImageButton.setVisibility(View.VISIBLE);
                } else {
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    manager.getBackStackEntryCount();
                    transaction.remove(fragment);
                    transaction.commit();
                    openFragment = false;
                    //topMenu.setPressed(false);
                    //closeMenuImageButton.setVisibility(View.VISIBLE);
                }

            }
        });
        closeMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (openFragment == true){
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentById(R.id.fragment);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    manager.getBackStackEntryCount();
                    transaction.remove(fragment);
                    transaction.commit();
                    openFragment = false;
                    topMenu.setVisibility(View.VISIBLE);
                    closeMenuImageButton.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
