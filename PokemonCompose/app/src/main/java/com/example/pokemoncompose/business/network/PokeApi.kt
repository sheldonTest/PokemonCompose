package com.example.pokemoncompose.business.network

import com.example.pokemoncompose.business.domain.model.PokeApiResponse
import com.example.pokemoncompose.business.domain.model.PokeDetail
import javax.inject.Inject

class PokeApi @Inject constructor(
    private val service: PokeApiService
){
    suspend fun getPokemon() : PokeApiResponse {
        return service.getPokemon()
    }

    suspend fun getPokemonDetail(id: Int) : PokeDetail {
        return service.getPokemonDetails(id)
    }

}