package com.example.pokemoncompose.framework.presentation.components

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemoncompose.business.domain.model.PokeDetail
import com.example.pokemoncompose.business.domain.model.Pokemon
import com.example.pokemoncompose.business.repository.PokeRepository
import com.example.pokemoncompose.util.ApiStateHolder
import com.example.pokemoncompose.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * This ViewModel provides
 * service delivered in the form
 * of StateFlows and State Holder objects
 * Data is emitted to the UI as State holder objects
 */
@HiltViewModel
class PokeViewModel @Inject constructor(
    private val repository: PokeRepository
) : ViewModel() {
    private var mutableState = MutableStateFlow(ApiStateHolder(State.LOADING, listOf<Pokemon>(),""))
    private var mutableDetailState = MutableStateFlow(ApiStateHolder(State.SUCCESS,
        PokeDetail(0,0,0, emptyList()),""))
    var sharePokmon = mutableStateOf<Pokemon?>(null)
    val query = mutableStateOf("")
    val stateFlow = mutableState.asStateFlow() //Used to reference in Composables
    val detailFlow = mutableDetailState.asStateFlow()

    fun getPokemon() {
        viewModelScope.launch {
            repository.getPokemon().catch {
                mutableState.value = ApiStateHolder.error(it.message)
                Timber.d("ViewModel.getPokemon Error: $it")
            }.collect {
                mutableState.value = ApiStateHolder.success(it.data)
            }
        }
    }

    fun getPokeDetail(id: Int) {
        viewModelScope.launch {
            repository.getPokemonDetails(id).catch {
                mutableDetailState.value = ApiStateHolder.error(it.message)
            }.collect{
                mutableDetailState.value = ApiStateHolder.success(it.data)
            }
        }
    }

     fun findPokemon(query: String) {
         viewModelScope.launch {
             val filteredList = ArrayList<Pokemon>()
             repository.getPokemonList().forEach { pokeman ->
                 if(pokeman.name.lowercase().contains(query.lowercase())) {
                     Timber.d("Found Pokemon: ${pokeman.name}")
                     filteredList.add(pokeman)
                 }
             }
             mutableState.value = ApiStateHolder.success(filteredList)
         }
    }

    fun accessPokemon(pokemon: Pokemon) {
        sharePokmon.value = pokemon
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

}