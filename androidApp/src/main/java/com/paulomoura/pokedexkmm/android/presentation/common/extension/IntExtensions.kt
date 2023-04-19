package com.paulomoura.pokedexkmm.android.presentation.common.extension

fun Int.toPokemonNumber() = "Nº ${String.format("%03d", this)}"