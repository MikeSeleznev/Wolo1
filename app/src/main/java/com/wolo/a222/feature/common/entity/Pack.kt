package com.wolo.a222.feature.common.entity

data class Pack(
        val id: String,
        val name: String,
        val tasks: List<String>,
        val paid: Boolean)