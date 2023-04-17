package com.paulomoura.pokedexkmm.android.presentation.pokemondetail

import com.paulomoura.pokedexkmm.domain.model.Pokemon

data class PokemonDetailState(
    val loading: Boolean = false,
    val pokemon: Pokemon? = null,
    val error: String = ""
)