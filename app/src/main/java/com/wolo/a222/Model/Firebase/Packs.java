package com.wolo.a222.Model.Firebase;

import java.util.List;

 public class Packs {

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
        int n = 0;
        String[] str = new String[usuall.size() - 1];
        for (String s: usuall) {
            if (!(s==null)){
                str[n] = s.toString();
            n+=1;}
        }
        return str;
    };

    public String[] getExtremeStringArray() {
        int n = 0;
        String[] str = new String[extreme.size() - 1];
        for (String s: extreme) {
            if (!(s==null)){
                str[n] = s.toString();
                n+=1;}
        }
        return str;
    }

    public String[] getSportStringArray() {

        int n = 0;
        String[] str = new String[sport.size() - 1];
        for (String s: sport) {
            if (!(s==null)){
                str[n] = s.toString();
                n+=1;}
        }
        return str;
    }

    public String[] getEroticStringArray() {

        int n = 0;
        String[] str = new String[erotic.size() - 1];
        for (String s: erotic) {
            if (!(s==null)){
                str[n] = s.toString();
                n+=1;}
        }
        return str;
    }

    public String[] getOhfuckStringArray() {

        int n = 0;
        String[] str = new String[ohfuck.size() - 1];
        for (String s: ohfuck) {
            if (!(s==null)){
                str[n] = s.toString();
                n+=1;}
        }
        return str;
    }
}