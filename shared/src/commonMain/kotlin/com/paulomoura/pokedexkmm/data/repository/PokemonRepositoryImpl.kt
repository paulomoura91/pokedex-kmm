package com.paulomoura.pokedexkmm.data.repository

import com.paulomoura.pokedexkmm.common.Response
import com.paulomoura.pokedexkmm.data.remote.ApiException
import com.paulomoura.pokedexkmm.data.remote.dto.PokemonDTO
import com.paulomoura.pokedexkmm.data.remote.dto.toPokemon
import com.paulomoura.pokedexkmm.data.remote.dto.toPokemonListItem
import com.paulomoura.pokedexkmm.data.remote.service.PokemonApiService
import com.paulomoura.pokedexkmm.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(private val pokemonApiService: PokemonApiService) : PokemonRepository {

    override suspend fun getPokemons() = flow {
        try {
            emit(Response.Loading())
            val pokemonDTOs: List<PokemonDTO> = pokemonApiService.getPokemons()
            val pokemonListItems = pokemonDTOs.map { it.toPokemonListItem() }
            emit(Response.Success(pokemonListItems))
        } catch (exception: Exception) {
            emit(Response.Error(ApiException(exception)))
        }
    }

    override suspend fun getPokemon(number: Int) = flow {
        try {
            emit(Response.Loading())
            val pokemonDTO = pokemonApiService.getPokemon(number)
            val evolutions = pokemonDTO.evolutions.map { evolutionNumber ->
                if (evolutionNumber == pokemonDTO.number) {
                    pokemonDTO
                } else {
                    pokemonApiService.getPokemon(evolutionNumber)
                }
            }
            val pokemon = pokemonDTO.toPokemon(evolutions)
            emit(Response.Success(pokemon))
        } catch (exception: Exception) {
            emit(Response.Error(ApiException(exception)))
        }
    }
}