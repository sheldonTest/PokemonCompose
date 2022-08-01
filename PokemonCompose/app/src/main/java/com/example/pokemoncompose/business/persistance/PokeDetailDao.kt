package com.example.pokemoncompose.business.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemoncompose.business.domain.model.PokeDetail

@Dao
interface PokeDetailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetails(detail: PokeDetail)

    @Query("SELECT * FROM PokeDetail WHERE :id = id")
    suspend fun getDetails(id: Int): PokeDetail?

}