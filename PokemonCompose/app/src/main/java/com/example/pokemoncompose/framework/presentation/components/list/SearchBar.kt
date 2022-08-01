package com.example.pokemoncompose.framework.presentation.components.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pokemoncompose.framework.presentation.components.PokeViewModel
import timber.log.Timber

@Composable
fun SearchBar(viewModel: PokeViewModel) {
    var query by remember { viewModel.query }

    TextField(value = query,
        onValueChange = {
            viewModel.onQueryChanged(it)
            if(it.isNotEmpty()) {
                viewModel.findPokemon(it)
                Timber.d("&&&&&&&& Value Changed: $it &&&&&&&&")
            }

    }, label = {
        Text("Poke Search")
        }, keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ), leadingIcon = {
            Icon(imageVector = Icons.Filled.Search,"")
        },
        singleLine = true,
    modifier = Modifier
        .fillMaxWidth()
        .background(
            color = MaterialTheme.colors.background,
            shape = RectangleShape))
}