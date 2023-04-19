package com.paulomoura.pokedexkmm.domain.usecase

import com.paulomoura.pokedexkmm.domain.repository.PokemonRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

class GetPokemonsUseCase(private val pokemonRepository: PokemonRepository) {

    @NativeCoroutines
    suspend fun getPokemons() = pokemonRepository.getPokemons()
}