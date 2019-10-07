package com.wolo.a222.feature.common.entity

data class Pack(
        val id: String = "",
        val name: String = "",
        val tasks: List<String> = emptyList(),
        val paid: Boolean = false,
        val activeImage: String = "",
        val nonActiveImage: String = "",
        val priority: Long = 0L,
        val restTasks: Int = 0
)