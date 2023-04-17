package com.paulomoura.pokedexkmm.domain.repository

import com.paulomoura.pokedexkmm.common.Response
import com.paulomoura.pokedexkmm.domain.model.Pokemon
import com.paulomoura.pokedexkmm.domain.model.PokemonListItem
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemons(): Flow<Response<List<PokemonListItem>>>

    suspend fun getPokemon(number: Int): Flow<Response<Pokemon>>
}