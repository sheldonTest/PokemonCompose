package com.example.pokemoncompose.business.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Pokemon(
    @field:Json(name = "name") @PrimaryKey val name: String,
    @field:Json(name = "url")val url: String) : Parcelable {

    fun getPokeImage(): String {
        val index = getNumber()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }

     fun getNumber(): String = url.split("/".toRegex()).dropLast(1).last()
}
