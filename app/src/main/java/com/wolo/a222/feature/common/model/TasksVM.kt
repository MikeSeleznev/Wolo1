package com.wolo.a222.feature.common.model

data class TasksVM(
    val id: String = "",
    val namePack: String = "",
    val quantity: Int = 0,
    val urlImage: String = "",
    var quantityNow: Int = 0,
    val isBought: Boolean = false,
    var tasks: List<String> = emptyList()
)