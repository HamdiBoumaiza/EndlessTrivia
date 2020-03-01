package com.hb.endlesstrivia.data

import com.hb.endlesstrivia.utils.TriviaCategory
import com.hb.endlesstrivia.utils.TriviaDifficulty
import com.hb.endlesstrivia.utils.TriviaNumber
import com.hb.endlesstrivia.utils.TriviaType


data class RequestListTrivia(
    @TriviaNumber val amount: String,
    @TriviaCategory val category: String?,
    @TriviaDifficulty val difficulty: String?,
    @TriviaType val type: String?
)