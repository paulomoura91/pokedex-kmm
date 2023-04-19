package com.paulomoura.pokedexkmm.di

import com.paulomoura.pokedexkmm.domain.usecase.GetPokemonUseCase
import com.paulomoura.pokedexkmm.domain.usecase.GetPokemonsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

@Suppress("unused") /*used by iOS*/
fun initKoin() = startKoin {
    modules(commonModule)
}

@Suppress("unused") /*used by iOS*/
class UseCasesHelper : KoinComponent {
    val getPokemonsUseCase: GetPokemonsUseCase by inject()
    val getPokemonUseCase: GetPokemonUseCase by inject()
}