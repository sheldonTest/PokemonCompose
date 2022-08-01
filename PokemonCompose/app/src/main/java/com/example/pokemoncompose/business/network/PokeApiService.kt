package com.example.pokemoncompose.business.network

import com.example.pokemoncompose.business.domain.model.PokeApiResponse
import com.example.pokemoncompose.business.domain.model.PokeDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemon(@Query("limit")limit: Int = 150) : PokeApiResponse
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id")id: Int) : PokeDetail
}