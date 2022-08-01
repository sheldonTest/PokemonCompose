package com.example.pokemoncompose.framework.presentation.components.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.pokemoncompose.R
import com.example.pokemoncompose.framework.presentation.components.PokeViewModel
import com.example.pokemoncompose.util.PokeProgressIndicator
import com.example.pokemoncompose.util.State
import com.example.pokemoncompose.util.loadPokeImage

/**
 * The detail screen composable uses state flow provided byo
 * the viewModel to check state and render list elements
 */


@Composable
fun PokeDetailScreen(viewModel: PokeViewModel) {
    val detailState = viewModel.detailFlow.collectAsState()
    val pokemon = viewModel.sharePokmon
    viewModel.getPokeDetail(pokemon.value!!.getNumber().toInt())

    detailState.apply {
        when(value.state) {
            State.SUCCESS -> {
                val detail = this.value.data
                Row(modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Magenta)
                    ) {
                    Column(modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioHighBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )) {
                        pokemon.value?.getPokeImage().let {
                            val image = loadPokeImage(url = it!!, defaultImage = R.drawable.avatar_icon)
                            image?.let { pokeman ->
                                Image(bitmap = pokeman.asImageBitmap(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(340.dp),
                                    contentScale = ContentScale.Crop, contentDescription = "Pokeman")
                            }
                        }
                        Text(text = "Height: ${detail!!.height}" ,style = MaterialTheme.typography.h4)
                        Text(text = "Weight: ${detail.weight}" ,style = MaterialTheme.typography.h4)
                        detail.types.forEach { 
                            Text(text = "Slot: ${it.slot}",style = MaterialTheme.typography.h4)
                            Text(text = "Type: ${it.types.name}",style = MaterialTheme.typography.h4)
                        }
                    }

                }

            }
            State.ERROR -> {
                Text(text = "Error Getting Poke Details",style = MaterialTheme.typography.h4)
            }
            State.LOADING -> {
                PokeProgressIndicator()
            }
        }
    }

}