package com.paulomoura.pokedexkmm.domain.usecase

import com.paulomoura.pokedexkmm.domain.repository.PokemonRepository

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemon(number: Int) = pokemonRepository.getPokemon(number)
}