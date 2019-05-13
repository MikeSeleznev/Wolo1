package com.wolo.a222;


public class Players {
    private String names;
    private float fromDegree;
    private float toDegree;
    private int number;
    private float centerDegree;



    /*public Players(List<String> names){
        this.names = names;
        //TODO do short names
        /*if (names.size()>0){
            for (String s: names) {
                char[] name1 = s.toCharArray();
                shortNames.put(s, Character.toString(name1[0]));
            }
        }*/


    public Players(String names, int num){
        this.names = names;
        this.number = num;
    }

    public int numberOfPlayers (){
        return this.names.length();
    }

    public String getShortName(){
        char[] name1 = this.names.toCharArray();
        return Character.toString(name1[0]);
    }

    public String getFullName(){
        return names;
    }

    public void setFromDegree(float d){
        this.fromDegree = d;
    }

    public void setToDegree(float d){
        this.toDegree = d;
    }

    public float getFromDegreeForPlayer(){
        return fromDegree;
    }

    public float getToDegreeForPlayer(){
        return toDegree;
    }

    public int getNumber(){
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        return super.equals(obj);
    }

    public void setCenterDegree(float d){
        this.centerDegree = d;
    }

    public float getCenterDegree(){
        return centerDegree;
    }


}
