package com.paulomoura.pokedexkmm.android.presentation.pokemonlist

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.paulomoura.pokedexkmm.android.presentation.Route
import com.paulomoura.pokedexkmm.android.presentation.pokemonlist.composable.ListPokemon
import com.paulomoura.pokedexkmm.android.presentation.ui.theme.PokedexComposeCleanTheme
import com.paulomoura.pokedexkmm.domain.model.PokemonListItem
import org.koin.androidx.compose.getViewModel

@Composable
fun PokemonListScreen(navController: NavController) {
    val viewModel: PokemonListViewModel = getViewModel()
    val pokemonListState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchTextState by viewModel.searchTextState.collectAsStateWithLifecycle()
    val onValueChange = viewModel::setSearchText
    if (pokemonListState.loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (pokemonListState.error.isNotBlank()) {
        Toast.makeText(LocalContext.current, pokemonListState.error, Toast.LENGTH_SHORT).show()
    } else {
        ListPokemon(
            pokemonListItems = pokemonListState.pokemonListItems,
            searchQuery = searchTextState,
            onValueChange = onValueChange
        ) { pokemonNumber ->
            navController.navigate(Route.PokemonDetail.navigateWithPokemonNumberArg(pokemonNumber))
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PokemonListScreenPreview() {
    PokedexComposeCleanTheme {
        val pokemons = mutableListOf<PokemonListItem>()
        for (i in 1..50) {
            pokemons.add(PokemonListItem("Pokémon $i", i, ""))
        }
        ListPokemon(pokemonListItems = pokemons, searchQuery = "", onValueChange = { }, onNavigateToDetail = { })
    }
}