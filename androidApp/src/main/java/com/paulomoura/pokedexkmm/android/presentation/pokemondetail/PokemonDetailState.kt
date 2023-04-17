package com.paulomoura.pokedexcomposeclean.presentation.pokemondetail

import com.paulomoura.pokedexcomposeclean.domain.model.Pokemon

data class PokemonDetailState(
    val loading: Boolean = false,
    val pokemon: Pokemon? = null,
    val error: String = ""
)