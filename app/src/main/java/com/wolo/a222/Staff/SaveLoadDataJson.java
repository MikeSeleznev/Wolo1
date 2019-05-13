package com.wolo.a222.Staff;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.wolo.a222.Firebase.Packs;
import com.wolo.a222.Game;
import com.wolo.a222.MainActivity;

public class SaveLoadDataJson <T> {

    public void saveData(Object tClass, Context context, String name){
        Gson gson = new Gson();
        String json = gson.toJson(tClass);
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(name, json);
        ed.commit();
    }
}
