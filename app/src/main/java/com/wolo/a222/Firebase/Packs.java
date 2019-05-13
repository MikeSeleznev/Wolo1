package com.wolo.a222.Firebase;

import java.util.ArrayList;
import java.util.List;

public class Packs {

    private List<String> usuall = null;
    private List<String> extreme = null;

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
}