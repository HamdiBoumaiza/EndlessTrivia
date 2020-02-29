package com.hb.endlesstrivia.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hb.endlesstrivia.model.Trivia

@Dao
interface AppDao {

    @Query("SELECT * FROM trivia")
    suspend fun getListTrivias(): List<Trivia>

    @Insert(onConflict = REPLACE)
    suspend fun setListTrivias(listTrivia: List<Trivia?>)

}