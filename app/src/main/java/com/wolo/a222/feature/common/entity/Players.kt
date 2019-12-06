package com.wolo.a222.feature.common.entity

class Players(val fullName: String, val number: Int) {

    var fromDegreeForPlayer = 0f
    var toDegreeForPlayer = 0f
    var centerDegree = 0f
    val shortName: String
        get() {
            val name1 = fullName.toCharArray()
            return name1[0].toString()
        }

    fun setFromDegree(d: Float) {
        fromDegreeForPlayer = d
    }

    fun setToDegree(d: Float) {
        toDegreeForPlayer = d
    }
}