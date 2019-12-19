package com.hb.endlesstrivia.data


data class RequestListTrivia(
    val amount: String,
    val category: String?,
    val difficulty: String?,
    val type: String?
)