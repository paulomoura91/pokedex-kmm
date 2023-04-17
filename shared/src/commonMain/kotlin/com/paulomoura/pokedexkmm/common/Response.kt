package com.paulomoura.pokedexkmm.common

sealed class Response<T>(val data: T? = null, val exception: Exception? = null) {
    class Loading<T> : Response<T>()
    class Success<T>(data: T?) : Response<T>(data = data)
    class Error<T>(exception: Exception?) : Response<T>(exception = exception)
}