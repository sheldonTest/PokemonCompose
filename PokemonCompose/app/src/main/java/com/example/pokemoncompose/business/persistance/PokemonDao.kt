package com.example.pokemoncompose.business.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemoncompose.business.domain.model.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPokeData(data: List<Pokemon>)

    @Query("SELECT * FROM Pokemon")
    suspend fun getAllPokemon() : List<Pokemon>
}