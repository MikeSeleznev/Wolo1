package com.wolo.a222.Model.Firebase;

import android.content.Context;

import com.wolo.a222.R;

import java.util.ArrayList;
import java.util.List;

 public class Packs {

    public Boolean loadedFromInternet = false;
    public List<String> usuall = null;
    public List<String> extreme = null;
    public List<String> sport = null;
    public List<String> erotic = null;
    public List<String> ohfuck = null;

    public List<String> getExtreme() {
        return extreme;
    }

    public void setExtreme(List<String> extreme) {
        this.extreme = extreme;
    }

    public List<String> getUsuall() {
        return usuall;
    }

    public void setUsuall(List<String> usuall) {
        this.usuall = usuall;

    }

    public List<String> getSport() {
        return sport;
    }

    public void setSport(List<String> sport) {
        this.sport = sport;

    }

    public List<String> getErotic() {
        return erotic;
    }

    public void setErotic(List<String> erotic) {
        this.erotic = erotic;

    }

    public List<String> getOhfuck() {
        return ohfuck;
    }

    public void setOhfuck(List<String> ohfuck) {
        this.ohfuck = ohfuck;

    }


    public String[] getUsuallStringArray() {
        deleteNull(usuall);
        return fromStringToArray(usuall);
    }

     private void deleteNull(List<String> name) {
         for (int i = 0; i < name.size(); i++){
             if (name.get(i)==null){
                 name.remove(i);
             }}
     }

     private String[] fromStringToArray(List<String> name){
         String[] str = new String[name.size()];
         for (int i = 0; i < name.size(); i++){
             if (name.get(i)!=null){
                 str[i] = name.get(i);
             }}
         return str;
     }

    public String[] getExtremeStringArray() {
        deleteNull(extreme);
        return fromStringToArray(extreme);
    }

    public String[] getSportStringArray() {
        deleteNull(sport);
        return fromStringToArray(sport);
    }

    public String[] getEroticStringArray() {
        deleteNull(erotic);
        return fromStringToArray(erotic);
    }

    public String[] getOhfuckStringArray() {
        deleteNull(ohfuck);
        return fromStringToArray(ohfuck);
    }

    public void setCards(Context context){
        String[] usuall = context.getResources().getStringArray(R.array.usuall);
        String[] extreme = context.getResources().getStringArray(R.array.extreme);
        String[] erotic = context.getResources().getStringArray(R.array.erotic);
        String[] ohfuck = context.getResources().getStringArray(R.array.ohfuck);
        String[] sport = context.getResources().getStringArray(R.array.sport);

        this.usuall = returnArrayList(usuall);
        this.extreme = returnArrayList(extreme);
        this.erotic = returnArrayList(erotic);
        this.ohfuck = returnArrayList(ohfuck);
        this.sport = returnArrayList(sport);


    }

    private ArrayList<String> returnArrayList(String[] name){
        ArrayList<String> list = new ArrayList<>();
        for (String s: name) {
            list.add(s);
        }
        return list;
    }
}