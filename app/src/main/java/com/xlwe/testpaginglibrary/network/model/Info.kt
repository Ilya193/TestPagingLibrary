package com.xlwe.testpaginglibrary.network.model

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)