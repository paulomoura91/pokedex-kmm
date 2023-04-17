package com.paulomoura.pokedexkmm.data.remote.service

import com.paulomoura.pokedexkmm.data.remote.dto.PokemonDTO

interface PokemonApiService {

    suspend fun getPokemons(): List<PokemonDTO>

    suspend fun getPokemon(number: Int): PokemonDTO
}