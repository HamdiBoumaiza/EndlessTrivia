package com.hb.endlesstrivia.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "trivia", primaryKeys = ["question"])
data class Trivia(

    @SerializedName("category")
    val category: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("difficulty")
    val difficulty: String,

    @SerializedName("question")
    val question: String,

    @SerializedName("correct_answer")
    val correct_answer: String,

    @SerializedName("incorrect_answers")
    val incorrect_answers: List<String>
) : Serializable