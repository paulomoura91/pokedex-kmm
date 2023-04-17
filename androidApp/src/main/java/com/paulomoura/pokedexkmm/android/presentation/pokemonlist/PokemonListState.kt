package com.paulomoura.pokedexkmm.android.presentation.pokemonlist

import com.paulomoura.pokedexkmm.domain.model.PokemonListItem

data class PokemonListState(
    val loading: Boolean = false,
    val pokemonListItems: List<PokemonListItem> = emptyList(),
    val error: String = ""
)