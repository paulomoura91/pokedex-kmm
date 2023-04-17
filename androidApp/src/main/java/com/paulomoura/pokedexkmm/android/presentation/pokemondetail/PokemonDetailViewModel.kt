package com.paulomoura.pokedexkmm.android.presentation.pokemondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulomoura.pokedexkmm.common.Response
import com.paulomoura.pokedexkmm.common.constants.ErrorConstants
import com.paulomoura.pokedexkmm.domain.usecase.GetPokemonUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val getPokemonUseCase: GetPokemonUseCase, private val pokemonNumber: Int) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailState())
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PokemonDetailState())

    init {
        getPokemon()
    }

    private fun getPokemon() {
        viewModelScope.launch {
            getPokemonUseCase.getPokemon(pokemonNumber).onEach { response ->
                _uiState.value = when (response) {
                    is Response.Loading -> PokemonDetailState(loading = true)
                    is Response.Success -> response.data?.let { PokemonDetailState(pokemon = it) }
                        ?: PokemonDetailState(error = ErrorConstants.ERROR_POKEMON_DETAIL_STATE)
                    is Response.Error -> PokemonDetailState(error = ErrorConstants.ERROR_POKEMON_DETAIL_STATE)
                }
            }.collect()
        }
    }
}