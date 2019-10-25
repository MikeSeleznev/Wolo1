package com.wolo.a222.feature.common.entity

data class Pack(
        val id: String = "",
        val name: String = "",
        val tasks: MutableList<String> = mutableListOf(),
        val paid: Boolean = false,
        val activeImage: String = "",
        val nonActiveImage: String = "",
        val priority: Long = 0L,
        val restTasks: Int = tasks.size,
        val alwaysActive: Boolean = false
)