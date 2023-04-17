package com.paulomoura.pokedexkmm.android.presentation.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paulomoura.pokedexkmm.common.Response
import com.paulomoura.pokedexkmm.common.constants.ErrorConstants
import com.paulomoura.pokedexkmm.domain.model.doesMatchSearch
import com.paulomoura.pokedexkmm.domain.usecase.GetPokemonsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PokemonListViewModel(private val getPokemonsUseCase: GetPokemonsUseCase) : ViewModel() {

    private val _searchTextState = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

    private val _uiState = MutableStateFlow(PokemonListState())
    val uiState = _uiState.combine(searchTextState) { pokemonListState: PokemonListState, query: String ->
        if (query.isBlank())
            pokemonListState
        else {
            val filteredPokemons = pokemonListState.pokemonListItems.filter { it.doesMatchSearch(query) }
            PokemonListState(pokemonListItems = filteredPokemons)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PokemonListState())

    init {
        getPokemons()
    }

    private fun getPokemons() {
        viewModelScope.launch {
            getPokemonsUseCase.getPokemons().onEach { response ->
                _uiState.value = when (response) {
                    is Response.Loading -> PokemonListState(loading = true)
                    is Response.Success -> PokemonListState(pokemonListItems = response.data ?: emptyList())
                    is Response.Error -> PokemonListState(error = ErrorConstants.ERROR_POKEMON_LIST_STATE)
                }
            }.collect()
        }
    }

    fun setSearchText(searchText: String) {
        _searchTextState.value = searchText
    }
}