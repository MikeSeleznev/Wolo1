package com.wolo.a222.View.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wolo.a222.Cards;
import com.wolo.a222.Const;
import com.wolo.a222.Model.Firebase.Packs;
import com.wolo.a222.Game;
import com.wolo.a222.Players;
import com.wolo.a222.Presenter.MainActivityPresenter;
import com.wolo.a222.R;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView gamersListView;
    Button startGame;
    Button addPlayer;
    EditText newUser;
    MyListAdapter myListAdapter;
    Players[] players;
    SharedPreferences sPref;
    Game game;
    Cards[] cards;
    ImageButton topMenu;
    Boolean openFragment;
    ImageButton closeMenuImageButton;
    MainActivityPresenter presenter;

    final ArrayList<String> gamersArray = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        presenter.bindView(this, this);
        Gson gson = new Gson();
        String json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "");
        game = gson.fromJson(json, Game.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        presenter = new MainActivityPresenter();
        openFragment = false;

        gamersListView = (ListView) findViewById(R.id.gamers);
        myListAdapter = new MyListAdapter(this, R.layout.list_row, gamersArray);
        gamersListView.setAdapter(myListAdapter);
        newUser = (EditText) findViewById(R.id.newUser);
        startGame = (Button) findViewById(R.id.startGame);
        startGame.setEnabled(false);
        topMenu = (ImageButton) findViewById(R.id.topmenu);
        closeMenuImageButton = (ImageButton) findViewById(R.id.closeMenuImageButton);
        closeMenuImageButton.setVisibility(View.INVISIBLE);

        //Gson gson = new Gson();
        //String json = PreferenceManager.getDefaultSharedPreferences(this).getString("game", "");
        //game = gson.fromJson(json, Game.class);





        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.setData(MainActivity.this, gamersArray);

                Intent intent = new Intent(MainActivity.this, GamezoneActivity.class);
                startActivity(intent);
            }
        });

        addPlayer = (Button) findViewById(R.id.add2);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = newUser.getText().toString();
                if (!inputString.equals("")) {
                    gamersArray.add(inputString);
                    //game.addPlayer(inputString, gamersArray);
                    myListAdapter.notifyDataSetChanged();
                    newUser.setText("");
                }

                setEnterItemVisible();
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




    private class MyListAdapter extends ArrayAdapter<String> {
        private int layout;

        private MyListAdapter(Context context, int resource, ArrayList<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if (convertView == null) {
                ViewHolder viewholder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                viewholder.title = (TextView) convertView.findViewById(R.id.title);
                viewholder.button = (ImageButton) convertView.findViewById(R.id.DeleteUser);
                viewholder.buttonUser =  convertView.findViewById(R.id.User);
                viewholder.buttonUser.setText(game.getShortNameFromString(getItem(position)));
                viewholder.title.setText(getItem(position));
                viewholder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gamersArray.remove(position);
                        myListAdapter.notifyDataSetChanged();
                        setEnterItemVisible();
                    }
                });

                convertView.setTag(viewholder);

            } else {
                mainViewholder = (ViewHolder) convertView.getTag();
                mainViewholder.title.setText(getItem(position));
            }
            return convertView;

        }
    }

    private void setEnterItemVisible() {
        boolean check = true;
        boolean enab = true;
        int checkSize = gamersArray.size();
        if (checkSize == 8) {
            check = false;
        }
        newUser.setEnabled(check);
        addPlayer.setEnabled(check);

        if(checkSize < 2){
            enab = false;
        }
        startGame.setEnabled(enab);
    }

    public class ViewHolder {

        public Button buttonUser;
        TextView title;
        ImageButton button;
    }

    protected void onStop() {
        super.onStop();
        presenter.unbindView(this);
    }
}
