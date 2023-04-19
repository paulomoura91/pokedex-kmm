package com.paulomoura.pokedexkmm.android.di

import com.paulomoura.pokedexkmm.android.presentation.pokemondetail.PokemonDetailViewModel
import com.paulomoura.pokedexkmm.android.presentation.pokemonlist.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        PokemonListViewModel(get())
    }
    viewModel {
        PokemonDetailViewModel(get(), get())
    }
}