package com.example.pokemoncompose.business.persistance

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.pokemoncompose.business.domain.model.PokeDetail
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class TypeArrayConverter @Inject constructor(
    private val moshi: Moshi
) { //Convert JSON Array

    @TypeConverter
    fun convertFromStringToList(datum: String) : List<PokeDetail.TypesArray>? {
        val listType = Types.newParameterizedType(List::class.java,PokeDetail.TypesArray::class.java)
        val jsonAdapter : JsonAdapter<List<PokeDetail.TypesArray>> = moshi.adapter(listType)

        return jsonAdapter.fromJson(datum)
    }

    @TypeConverter
    fun convertListToString(list: List<PokeDetail.TypesArray>?) : String {
        val listType = Types.newParameterizedType(List::class.java,PokeDetail.TypesArray::class.java)
        val jsonAdapter : JsonAdapter<List<PokeDetail.TypesArray>> = moshi.adapter(listType)

        return jsonAdapter.toJson(list)
    }
}