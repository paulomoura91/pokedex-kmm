package com.paulomoura.pokedexkmm.data.remote.dto

import com.paulomoura.pokedexkmm.domain.model.Pokemon
import com.paulomoura.pokedexkmm.domain.model.PokemonListItem
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDTO(
    val number: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val types: List<String>,
    val evolutions: List<Int>
)

fun PokemonDTO.toPokemonListItem(): PokemonListItem {
    return PokemonListItem(
        name = name,
        number = number,
        imageUrl = imageUrl
    )
}

fun PokemonDTO.toPokemon(pokemonDTOEvolutions: List<PokemonDTO>): Pokemon {
    return Pokemon(
        number = number,
        name = name,
        description = description,
        imageUrl = imageUrl,
        types = types,
        evolutions = pokemonDTOEvolutions.map { it.toEvolution() }
    )
}

private fun PokemonDTO.toEvolution(): Pokemon {
    return Pokemon(
        number = number,
        name = name,
        description = description,
        imageUrl = imageUrl,
        types = types
    )
}