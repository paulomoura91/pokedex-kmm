package com.paulomoura.pokedexkmm.android.presentation.pokemonlist.composable

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.paulomoura.pokedexkmm.android.presentation.common.composable.SearchTextField
import com.paulomoura.pokedexkmm.android.presentation.common.composable.debugPlaceholder
import com.paulomoura.pokedexkmm.domain.model.PokemonListItem

@Composable
fun ListPokemon(
    pokemonListItems: List<PokemonListItem>,
    searchQuery: String,
    onValueChange: (String) -> Unit,
    onNavigateToDetail: (Int) -> Unit
) {
    BackHandler(searchQuery.isNotBlank()) {
        onValueChange("")
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        SearchTextField(searchQuery = searchQuery, onValueChange = onValueChange)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(pokemonListItems) { pokemon ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onNavigateToDetail(pokemon.number)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
                        placeholder = debugPlaceholder(com.paulomoura.pokedexkmm.android.R.mipmap.pokemon_placeholder)
                    )
                    Text(
                        text = String.format("%03d", pokemon.number),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 24.dp, top = 8.dp, bottom = 8.dp)
                    )
                    Text(
                        text = pokemon.name,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 24.dp, top = 8.dp, bottom = 8.dp)
                    )
                }
            }
        }
    }
}