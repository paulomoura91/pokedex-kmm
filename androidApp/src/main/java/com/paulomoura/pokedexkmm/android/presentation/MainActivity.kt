package com.paulomoura.pokedexkmm.android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paulomoura.pokedexkmm.android.presentation.pokemondetail.PokemonDetailScreen
import com.paulomoura.pokedexkmm.android.presentation.pokemonlist.PokemonListScreen
import com.paulomoura.pokedexkmm.android.presentation.ui.resources.APP_NAME
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.PokedexComposeCleanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeCleanTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = { ShowToolbar(showBackButton = shouldShowBackButton(navController = navController), navController::navigateUp) },
                    content = {
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colors.background
                        ) {
                            NavHost(navController = navController, startDestination = Route.PokemonList.value) {
                                composable(route = Route.PokemonList.value) {
                                    PokemonListScreen(navController = navController)
                                }
                                composable(
                                    route = Route.PokemonDetail.routeWithPokemonNumberArg(),
                                    arguments = listOf(navArgument(Route.PokemonDetail.POKEMON_NUMBER_ARG) { type = NavType.IntType })
                                ) { backStackEntry ->
                                    backStackEntry.arguments?.getInt(Route.PokemonDetail.POKEMON_NUMBER_ARG)
                                        ?.let { PokemonDetailScreen(pokemonNumber = it) }
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun ShowToolbar(showBackButton: Boolean, backPress: () -> Boolean) {
        if (showBackButton) {
            TopAppBar(
                title = {
                    Text(
                        text = APP_NAME,
                        color = Color.White
                    )
                },
                backgroundColor = Color.Red,
                navigationIcon = {
                    IconButton(onClick = { backPress() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            null,
                            tint = Color.White
                        )
                    }
                }
            )
        } else {
            TopAppBar(
                title = {
                    Text(
                        text = APP_NAME,
                        color = Color.White
                    )
                },
                backgroundColor = Color.Red
            )
        }
    }

    @Composable
    private fun shouldShowBackButton(navController: NavController): Boolean {
        val currentRoute = navController.currentBackStackEntryFlow.collectAsState(navController.currentBackStackEntry)
        return currentRoute.value?.destination?.route != Route.PokemonList.value
    }
}

sealed class Route(val value: String) {
    object PokemonList : Route("pokemon_list")
    object PokemonDetail : Route("pokemon_detail") {
        const val POKEMON_NUMBER_ARG = "pokemonNumber"
        fun routeWithPokemonNumberArg() = "$value/{$POKEMON_NUMBER_ARG}"
        fun navigateWithPokemonNumberArg(pokemonNumber: Int) = "$value/$pokemonNumber"
    }
}