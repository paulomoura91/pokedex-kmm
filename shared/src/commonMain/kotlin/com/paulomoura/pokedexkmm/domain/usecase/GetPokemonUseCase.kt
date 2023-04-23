package com.paulomoura.pokedexkmm.domain.usecase

import com.paulomoura.pokedexkmm.domain.repository.PokemonRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

class GetPokemonUseCase(private val pokemonRepository: PokemonRepository) {

    @NativeCoroutines
    suspend fun getPokemon(number: Int) = pokemonRepository.getPokemon(number)
}