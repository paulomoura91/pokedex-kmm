package com.paulomoura.pokedexkmm.domain.model

import com.paulomoura.pokedexkmm.formatString

data class PokemonListItem(
    val name: String,
    val number: Int,
    val imageUrl: String
)

data class Pokemon(
    val name: String,
    val number: Int,
    val imageUrl: String,
    val description: String,
    val types: List<String>,
    val evolutions: List<Pokemon> = emptyList()
)

fun PokemonListItem.doesMatchSearch(query: String): Boolean {
    val matchingPossibilities = listOf(formatString("%03d", number), name)
    return matchingPossibilities.any { it.contains(query, ignoreCase = true) }
}