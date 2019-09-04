package com.wolo.a222;

import android.content.Intent;
import android.preference.PreferenceManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wolo.a222.View.Activity.ShopActivity;
import com.wolo.a222.View.Activity.TaskActivity;
import com.wolo.a222.View.Activity.TopMenuActivity;
import com.wolo.a222.feature.common.model.Game;


public class SelectActivity extends AppCompatActivity {

    TextView user;
    ImageButton usual;
    ImageButton extreme;
    ImageButton closeMenuImageButton;
    ImageButton topMenu;
    Boolean openFragment;
    CheckedTextView numCardsUsuall;
    CheckedTextView kolodanumcards2;
    CheckedTextView kolodanumcards3; //sport
    CheckedTextView kolodanumcards5; //erotic
    CheckedTextView kolodanumcards6;//ohfuck

    ImageButton sport;
    ImageButton ohfuck;
    ImageButton erotic;
    ImageView closedSport;
    ImageView closedErotic;
    ImageView closedOhFuck;
    Game game;
    Intent taskIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openFragment = false;
        setContentView(R.layout.select_activity);
        topMenu = (ImageButton) findViewById(R.id.topmenuSelectActivity);
        closeMenuImageButton = (ImageButton) findViewById(R.id.closeMenuImageButtonSelectActivity);
        closeMenuImageButton.setVisibility(View.INVISIBLE);

        sport = findViewById(R.id.SPORT);
        closedSport = findViewById(R.id.closedSport);

        erotic = findViewById(R.id.EROTIC);
        closedErotic = findViewById(R.id.closedErotic);

        ohfuck = findViewById(R.id.OHFUCK);
        closedOhFuck = findViewById(R.id.closedOhFuck);

        numCardsUsuall = (CheckedTextView) findViewById(R.id.numCardsUsuall);
        kolodanumcards2 = (CheckedTextView) findViewById(R.id.numCardsExtreme);
        kolodanumcards3 = (CheckedTextView) findViewById(R.id.kolodanumcards3);
        kolodanumcards5 = (CheckedTextView) findViewById(R.id.kolodanumcards5);
        kolodanumcards6 = (CheckedTextView) findViewById(R.id.kolodanumcards6);

        user = (TextView)findViewById(R.id.selectedUser);


        usual = (ImageButton) findViewById(R.id.usual);
        usual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.cards[0].getLeftCards() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "В колоде закончились карты", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Intent taskIntent = new Intent(SelectActivity.this, TaskActivity.class);
                    taskIntent.putExtra("pack", Const.USUAL);
                    startActivity(taskIntent);
                    finish();
                }
            }
        });

        extreme = (ImageButton) findViewById(R.id.extreme);
        extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.cards[1].getLeftCards() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "В колоде закончились карты", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                Intent taskIntent = new Intent(SelectActivity.this, TaskActivity.class);
                taskIntent.putExtra("pack", Const.EXTREME);
                startActivity(taskIntent);
                finish();
            }}
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

    @Override
    protected void onStart() {
        super.onStart();
        Gson gson = new Gson();
        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "");
        game = gson.fromJson(json, Game.class);

        numCardsUsuall.setText(game.cards[0].leftCardsInt());
        kolodanumcards2.setText(game.cards[1].leftCardsInt());
        kolodanumcards3.setText(game.cards[2].leftCardsInt());
        kolodanumcards5.setText(game.cards[3].leftCardsInt());
        kolodanumcards6.setText(game.cards[4].leftCardsInt());

        user.setText(game.getChoosedPlayer().getFullName());


        if (game.paidSport){
            sport.setImageResource(R.drawable.sport);
            closedSport.setVisibility(View.INVISIBLE);
        }

        if (game.paidErotic){
            erotic.setImageResource(R.drawable.erotic);
            closedErotic.setVisibility(View.INVISIBLE);
        }

        if (game.paidOhfuck){
            ohfuck.setImageResource(R.drawable.ohfuck);
            closedOhFuck.setVisibility(View.INVISIBLE);
        }

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (game.cards[2].getLeftCards() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "В колоде закончились карты", Toast.LENGTH_SHORT);
                    toast.show();
                } else {


                if (game.paidSport){
                    taskIntent = new Intent(SelectActivity.this, TaskActivity.class);
                }else taskIntent = new Intent(SelectActivity.this, ShopActivity.class);

                //taskIntent = new Intent(SelectActivity.this, TaskActivity.class);

                taskIntent.putExtra("pack", Const.SPORT);
                startActivity(taskIntent);
            }}
        });

        erotic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.cards[3].getLeftCards() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "В колоде закончились карты", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                if (game.paidErotic){
                    taskIntent = new Intent(SelectActivity.this, TaskActivity.class);
                }else taskIntent = new Intent(SelectActivity.this, ShopActivity.class);

                //taskIntent = new Intent(SelectActivity.this, TaskActivity.class);

                taskIntent.putExtra("pack", Const.EROTIC);
                startActivity(taskIntent);
            }}
        });

        ohfuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.cards[4].getLeftCards() == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "В колоде закончились карты", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                if (game.paidOhfuck) {
                    taskIntent = new Intent(SelectActivity.this, TaskActivity.class);
                }else taskIntent = new Intent(SelectActivity.this, ShopActivity.class);

                //taskIntent = new Intent(SelectActivity.this, TaskActivity.class);

                taskIntent.putExtra("pack", Const.OHFUCK);
                startActivity(taskIntent);
            }}
        });
    }
}
