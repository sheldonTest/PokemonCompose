package com.example.pokemoncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemoncompose.framework.presentation.components.PokeViewModel
import com.example.pokemoncompose.framework.presentation.components.detail.PokeDetailScreen
import com.example.pokemoncompose.framework.presentation.components.list.PokeList
import com.example.pokemoncompose.framework.presentation.components.list.SearchBar
import com.example.pokemoncompose.ui.theme.PokemonComposeTheme
import com.example.pokemoncompose.util.DeviceSize
import com.example.pokemoncompose.util.DeviceType
import com.example.pokemoncompose.util.calculateDeviceSize
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Hilt entry point and container for compose UI
 * components.
 *
 * NOTE: No Fragments are needed to host screen composables
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<PokeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            PokemonComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Utility to calculate Device screen sizes
                    val deviceSize = calculateDeviceSize()
                  PokeScreen(viewModel = viewModel,navController,deviceSize)
                }
            }
        }
    }
}

/**
 * Establish Navigation graph for the
 * List and Detail screens
 */

@Composable
fun PokeScreen(viewModel: PokeViewModel,
               navController: NavHostController,
               deviceSize: DeviceSize) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            HomeScreenList(viewModel = viewModel, navController = navController,deviceSize)
        }
        composable("detail") {
            PokeDetailScreen( viewModel = viewModel)
        }
    }

}

@Composable
fun HomeScreenList(viewModel: PokeViewModel,
                   navController: NavHostController,
                   deviceSize: DeviceSize) {

    //Check to see if the device is Expanded or Compact
    //provide a different layout based on calculation
    when(deviceSize.height) {
        DeviceType.Expanded -> {
            Row() {
                PokeList(viewModel = viewModel,navController)
            }
        }
        else -> {
            Column() {
                SearchBar(viewModel = viewModel)
                PokeList(viewModel = viewModel,navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokemonComposeTheme {
    }
}