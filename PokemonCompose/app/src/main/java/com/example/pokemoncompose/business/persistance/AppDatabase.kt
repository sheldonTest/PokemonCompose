package com.example.pokemoncompose.business.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemoncompose.business.domain.model.PokeDetail
import com.example.pokemoncompose.business.domain.model.Pokemon

@Database(entities = [Pokemon::class, PokeDetail::class], version = 1, exportSchema = false)
@TypeConverters(value = [TypeArrayConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao() : PokemonDao
    abstract fun pokemonDetailDao() : PokeDetailDao
}