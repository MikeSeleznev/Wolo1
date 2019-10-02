package com.wolo.a222.feature.common.model

import com.wolo.a222.Model.Firebase.Packs
import java.util.*


class Cards(val name: String, cards: ArrayList<String>) {
    var leftCards: Int = 0
        private set
    private val allcards: Int
    private val cards: MutableList<String>
    private val packs: Packs? = null

    val randomQuestion: String
        get() {
            val number = Random()
            val r1 = number.nextInt(leftCards)
            val s = cards[r1]
            cards.removeAt(r1)
            return s

        }

    init {
        this.cards = cards
        this.leftCards = cards.size
        this.allcards = cards.size
    }

    fun sizeCards(): Int {
        return cards.size
    }

    fun minusOneCard() {
        leftCards = leftCards - 1
    }

    fun leftCardsText(): String {
        val str = StringBuilder()
        str.append("Осталось карт в колоде ")
        str.append(cards.size)
        str.append("/")
        str.append(allcards)

        return str.toString()
    }

    fun leftCardsInt(): String {
        val str = StringBuilder()
        str.append(leftCards)
        str.append("/")
        str.append(allcards)

        return str.toString()
    }

    fun getCards(): List<String> {
        return cards
    }

    fun setLeftCards() {
        leftCards -= 1
    }
}
