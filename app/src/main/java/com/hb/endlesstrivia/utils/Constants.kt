package com.hb.endlesstrivia.utils

import androidx.annotation.StringDef

const val TRIVIA_EXTRA = "details_ trivia"

const val TRIVIA_BOOLEAN_TRUE = "True"
const val TRIVIA_BOOLEAN_FALSE = "False"

const val TRIVIA_TYPE_BOOLEAN = "boolean"
const val TRIVIA_TYPE_MULTIPLE = "multiple"

@StringDef(
    value = [TRIVIA_TYPE_BOOLEAN,
        TRIVIA_TYPE_MULTIPLE]
)
@Retention(AnnotationRetention.SOURCE)
annotation class TriviaType

const val TRIVIA_NUMBER_10 = "10"
const val TRIVIA_NUMBER_20 = "20"
const val TRIVIA_NUMBER_30 = "30"
const val TRIVIA_NUMBER_40 = "40"
const val TRIVIA_NUMBER_50 = "50"

@StringDef(
    value = [TRIVIA_NUMBER_10,
        TRIVIA_NUMBER_20,
        TRIVIA_NUMBER_30,
        TRIVIA_NUMBER_40,
        TRIVIA_NUMBER_50]
)
@Retention(AnnotationRetention.SOURCE)
annotation class TriviaNumber

const val TRIVIA_DIFFICULTY_EASY = "easy"
const val TRIVIA_DIFFICULTY_MEDIUM = "Medium"
const val TRIVIA_DIFFICULTY_HARD = "Hard"

@StringDef(value = [TRIVIA_DIFFICULTY_EASY, TRIVIA_DIFFICULTY_MEDIUM, TRIVIA_DIFFICULTY_HARD])
@Retention(AnnotationRetention.SOURCE)
annotation class TriviaDifficulty


const val TRIVIA_CATEGORY_ANY = ""
const val TRIVIA_CATEGORY_GENERAL_KNOWLEDGE = "9"
const val TRIVIA_CATEGORY_SCIENCE = "17"
const val TRIVIA_CATEGORY_SPORTS = "21"
const val TRIVIA_CATEGORY_CELEBRITIES = "26"
const val TRIVIA_CATEGORY_ANIMALS = "27"
const val TRIVIA_CATEGORY_VEHICLES = "28"

@StringDef(
    value = [TRIVIA_CATEGORY_ANY,
        TRIVIA_CATEGORY_GENERAL_KNOWLEDGE,
        TRIVIA_CATEGORY_SCIENCE,
        TRIVIA_CATEGORY_SPORTS,
        TRIVIA_CATEGORY_CELEBRITIES,
        TRIVIA_CATEGORY_ANIMALS,
        TRIVIA_CATEGORY_VEHICLES
    ]
)
@Retention(AnnotationRetention.SOURCE)
annotation class TriviaCategory