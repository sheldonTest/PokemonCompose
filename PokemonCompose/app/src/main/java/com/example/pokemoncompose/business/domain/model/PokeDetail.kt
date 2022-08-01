package com.example.pokemoncompose.business.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class PokeDetail(
    @field:Json(name = "id") @PrimaryKey val id: Int,
    @field:Json(name = "height") val height:Int,
    @field:Json(name = "weight") val weight: Int,
    @field:Json(name = "types") val types: List<TypesArray>) {

    @JsonClass(generateAdapter = true)
    data class TypesArray(
        @field:Json(name = "slot") val slot: Int,
        @field:Json(name = "type") val types: Type
    )

    @JsonClass(generateAdapter = true)
    data class Type(
        @field:Json(name = "name") val name: String)

}


