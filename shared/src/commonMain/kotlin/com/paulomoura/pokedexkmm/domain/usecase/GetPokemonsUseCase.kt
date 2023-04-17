package com.paulomoura.pokedexkmm.domain.usecase

import com.paulomoura.pokedexkmm.domain.repository.PokemonRepository

class GetPokemonsUseCase(private val pokemonRepository: PokemonRepository) {

    suspend fun getPokemons() = pokemonRepository.getPokemons()
}