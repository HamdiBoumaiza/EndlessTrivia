package com.hb.endlesstrivia.data

import com.google.gson.annotations.SerializedName
import com.hb.endlesstrivia.model.Trivia


data class ResponseListTrivia(

    @SerializedName("response_code")
    val response_code: Int,

    @SerializedName("results")
    val results: List<Trivia>
)