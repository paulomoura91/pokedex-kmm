package com.paulomoura.pokedexcomposeclean.presentation.pokemonlist

import com.paulomoura.pokedexcomposeclean.domain.model.PokemonListItem

data class PokemonListState(
    val loading: Boolean = false,
    val pokemonListItems: List<PokemonListItem> = emptyList(),
    val error: String = ""
)