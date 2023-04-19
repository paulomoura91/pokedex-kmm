package com.paulomoura.pokedexkmm.di

import com.paulomoura.pokedexkmm.common.constants.HttpConstants
import com.paulomoura.pokedexkmm.data.remote.logHttpClientMessage
import com.paulomoura.pokedexkmm.data.remote.service.PokemonApiService
import com.paulomoura.pokedexkmm.data.remote.service.PokemonApiServiceImpl
import com.paulomoura.pokedexkmm.data.repository.PokemonRepositoryImpl
import com.paulomoura.pokedexkmm.domain.repository.PokemonRepository
import com.paulomoura.pokedexkmm.domain.usecase.GetPokemonUseCase
import com.paulomoura.pokedexkmm.domain.usecase.GetPokemonsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = object: Logger {
                    override fun log(message: String) { logHttpClientMessage(message) }
                }
                level = LogLevel.ALL
            }
            install(HttpTimeout) {
                requestTimeoutMillis = HttpConstants.HTTP_TIMEOUT
                connectTimeoutMillis = HttpConstants.HTTP_TIMEOUT
                socketTimeoutMillis = HttpConstants.HTTP_TIMEOUT
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTP
                    host = HttpConstants.HOST
                }
            }
        }
    }

    single<PokemonApiService> {
        PokemonApiServiceImpl(get())
    }

    single<PokemonRepository> {
        PokemonRepositoryImpl(get())
    }

    single {
        GetPokemonsUseCase(get())
    }

    single {
        GetPokemonUseCase(get())
    }
}