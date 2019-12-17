package com.hb.endlesstrivia.ui

import com.hb.endlesstrivia.model.Trivia


interface TriviaItemClickListener {
    fun onTriviaItemClicked(trivia: Trivia)
}