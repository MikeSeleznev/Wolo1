package com.wolo.a222;

import com.wolo.a222.Firebase.Packs;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Cards {
    private String name;
    private int leftCards;
    private int allcards;
    private List<String> cards;
    private Packs packs;

    public Cards(String name, String[] cards){
        this.name = name;
        this.cards = Arrays.asList(cards);
        this.leftCards = cards.length;
        this.allcards = cards.length;
    }

    public String getName(){
        return name;
    }

    public int sizeCards(){
        return cards.size();
    }

    public String getRandomQuestion(){
        Random number = new Random();
        int r1 = number.nextInt(leftCards);
        return cards.get(r1);
    }

    public void minusOneCard(){
        leftCards = leftCards - 1;
    }

    public String leftCardsText(){
        StringBuilder str = new StringBuilder();
        str.append("Осталось карт в колоде ");
        str.append(cards.size());
        str.append("/");
        str.append(allcards);

        return str.toString();
    }

    public String leftCardsInt(){
        StringBuilder str = new StringBuilder();
        str.append(leftCards);
        str.append("/");
        str.append(allcards);

        return str.toString();
    }

    public List<String> getCards(){
        return cards;
    }

    public void setLeftCards(){
        leftCards -= 1;
    }
}
