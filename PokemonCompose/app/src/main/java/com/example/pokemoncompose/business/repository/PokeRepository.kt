package com.example.pokemoncompose.business.repository

import com.example.pokemoncompose.business.domain.model.PokeDetail
import com.example.pokemoncompose.business.domain.model.Pokemon
import com.example.pokemoncompose.business.network.PokeApi
import com.example.pokemoncompose.business.persistance.PokeDetailDao
import com.example.pokemoncompose.business.persistance.PokemonDao
import com.example.pokemoncompose.util.ApiStateHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

/**
 * The repository utilizes Flow to return
 * the API data and inserts data into the Room Databas
 * Flow methods will return a state holder instead of
 * the API data object proper.
 */
class PokeRepository @Inject constructor(
    private val api: PokeApi,
    private val pokemonDao: PokemonDao,
    private val pokeDetailDao: PokeDetailDao
) : BaseRepository {

    override fun getPokemon(): Flow<ApiStateHolder<List<Pokemon>>> {
        return flow {
            var pokeman = pokemonDao.getAllPokemon()
            if(pokeman.isEmpty()) {
                val retVal = api.getPokemon()
                pokeman = retVal.results
                if (pokeman.isEmpty()) {
                    emit(ApiStateHolder.error("Error Getting Pokemon Data"))
                }
                else {
                    pokeman.forEach {
                        Timber.d("!!!!!!!!! Pokemans: ${it.name} !!!!!!!!!!!!!")
                    }
                    pokemonDao.insertPokeData(pokeman)
                    emit(ApiStateHolder.success(pokeman))
                }
            }
            else {
                Timber.d("%%%%%%%% Emit Cached Pokeman List %%%%%%%%")
                emit(ApiStateHolder.success(pokemonDao.getAllPokemon()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPokemonList() : List<Pokemon> = pokemonDao.getAllPokemon()

    override fun getPokemonDetails(id: Int): Flow<ApiStateHolder<PokeDetail>> {
        return flow {
            val detail = pokeDetailDao.getDetails(id)
            if(detail == null) {
                val pokeDetail = api.getPokemonDetail(id)
                Timber.d("Height: ${pokeDetail.height}")
                Timber.d("Weight: ${pokeDetail.weight}")
                pokeDetailDao.insertDetails(pokeDetail)
                emit(ApiStateHolder.success(pokeDetail))
            }
            else {
                Timber.d("%%%%%%%% Emit Cached Pokeman Details %%%%%%%%")
                emit(ApiStateHolder.success(pokeDetailDao.getDetails(id)))
            }
        }.flowOn(Dispatchers.IO)
    }
}