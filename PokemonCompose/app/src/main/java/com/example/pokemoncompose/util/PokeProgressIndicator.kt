package com.example.pokemoncompose.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PokeProgressIndicator() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            modifier = Modifier.size(300.dp),
            color = Color.Green,
            strokeWidth = 50.dp)
    }
}