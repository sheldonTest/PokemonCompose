package com.example.pokemoncompose.business.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeApiResponse(
    @field:Json(name = "results") val results: List<Pokemon>)