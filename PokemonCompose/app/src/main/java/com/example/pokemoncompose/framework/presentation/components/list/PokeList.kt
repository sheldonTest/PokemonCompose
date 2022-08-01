package com.example.pokemoncompose.framework.presentation.components.list


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokemoncompose.framework.presentation.components.PokeViewModel
import com.example.pokemoncompose.util.PokeProgressIndicator
import com.example.pokemoncompose.util.State

/**
 * The list composable uses state flow provided byo
 * the viewModel to check state and render list elements
 */

@Composable
fun PokeList(viewModel: PokeViewModel,navController: NavHostController) {
    val uiState = viewModel.stateFlow.collectAsState()
    viewModel.getPokemon()
    uiState.apply {
        when(value.state) {
            State.SUCCESS -> {
                LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
                    itemsIndexed(items = value.data!!) { index, pokemon ->
                        Card(modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                            backgroundColor = MaterialTheme.colors.primary) {
                            PokeCard(pokemon = pokemon) {
                                viewModel.accessPokemon(pokemon)
                                navController.navigate("detail")
                            }
                        }
                    }
                }
            }
            State.ERROR -> {
                Text(text = "Error No Pokemans???")
            }
            State.LOADING -> {
                PokeProgressIndicator()
            }
        }
    }

}