package com.hb.endlesstrivia.ui.list_trivia

import com.hb.endlesstrivia.model.Trivia


interface TriviaItemClickListener {
    fun onTriviaItemClicked(trivia: Trivia)
}