package com.example.pokemoncompose.business.repository

import androidx.annotation.WorkerThread
import com.example.pokemoncompose.business.domain.model.PokeDetail
import com.example.pokemoncompose.business.domain.model.Pokemon
import com.example.pokemoncompose.util.ApiStateHolder
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    @WorkerThread
    fun getPokemon() : Flow<ApiStateHolder<List<Pokemon>>>
    @WorkerThread
    fun getPokemonDetails(id: Int) : Flow<ApiStateHolder<PokeDetail>>
}