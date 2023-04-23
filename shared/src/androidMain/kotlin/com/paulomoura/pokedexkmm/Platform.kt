package com.paulomoura.pokedexkmm

actual fun formatString(format: String, vararg args: Any?): String {
    return java.lang.String.format(format, *args)
}