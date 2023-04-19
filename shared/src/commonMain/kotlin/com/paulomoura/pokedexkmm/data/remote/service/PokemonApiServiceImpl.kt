package com.paulomoura.pokedexkmm.data.remote.service

import com.paulomoura.pokedexkmm.common.constants.HttpConstants
import com.paulomoura.pokedexkmm.data.remote.dto.PokemonDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class PokemonApiServiceImpl(private val httpClient: HttpClient) : PokemonApiService {

    override suspend fun getPokemons(): List<PokemonDTO> {
        val httpResponse = httpClient.get {
            url {
                appendPathSegments(HttpConstants.ApiRoutes.POKEMON)
            }
        }
        return httpResponse.body()
    }

    override suspend fun getPokemon(number: Int): PokemonDTO {
        val httpResponse = httpClient.get {
            url {
                appendPathSegments(HttpConstants.ApiRoutes.POKEMON, number.toString())
            }
        }
        return httpResponse.body()
    }
}