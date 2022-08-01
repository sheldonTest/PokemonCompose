package com.example.pokemoncompose.framework.presentation.components.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.pokemoncompose.R
import com.example.pokemoncompose.business.domain.model.Pokemon
import com.example.pokemoncompose.util.loadPokeImage

@Composable
fun PokeCard(
    pokemon: Pokemon,
    onClick: () -> Unit) {
    
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick), elevation = 8.dp) {

        Column() {
            pokemon.getPokeImage().let {
                val image = loadPokeImage(url = it, defaultImage = R.drawable.avatar_icon)
                image?.let { pokeman ->
                    Image(bitmap = pokeman.asImageBitmap(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(340.dp),
                        contentScale = ContentScale.Crop, contentDescription = "Pokeman")
                }
            }
            Row(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()) {
                Text(text = pokemon.name,
                    style = MaterialTheme.typography.h2)
                Text(text = pokemon.getNumber(),
                    modifier = Modifier.padding(start = 10.dp), style = MaterialTheme.typography.h2)
            }

        }
        
    }

}