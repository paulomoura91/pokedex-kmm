package com.paulomoura.pokedexkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform