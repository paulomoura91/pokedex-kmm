package com.paulomoura.pokedexkmm.data.remote.service

import com.paulomoura.pokedexkmm.common.constants.HttpConstants
import com.paulomoura.pokedexkmm.data.remote.dto.PokemonDTO
import io.ktor.client.*
import io.ktor.client.request.*

class PokemonApiServiceImpl(private val httpClient: HttpClient) : PokemonApiService {

    override suspend fun getPokemons(): List<PokemonDTO> = httpClient.get {
        url {
            path(HttpConstants.ApiRoutes.POKEMON)
        }
    }

    override suspend fun getPokemon(number: Int): PokemonDTO = httpClient.get {
        url {
            path(HttpConstants.ApiRoutes.POKEMON, number.toString())
        }
    }
}