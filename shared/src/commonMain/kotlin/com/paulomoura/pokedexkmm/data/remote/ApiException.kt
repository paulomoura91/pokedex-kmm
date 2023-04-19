package com.paulomoura.pokedexkmm.data.remote

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

class ApiException private constructor(
    apiMessage: String
) : Exception(apiMessage) {

    companion object {
        operator fun invoke(exception: Exception): ApiException {
            val apiMessage = when (exception) {
                is RedirectResponseException -> "$REDIRECT_RESPONSE_EXCEPTION: $exception"
                is ClientRequestException -> "$CLIENT_REQUEST_EXCEPTION: $exception"
                is ServerResponseException -> "$SERVER_RESPONSE_EXCEPTION: $exception"
                else -> "$UNKNOWN_EXCEPTION: $exception"
            }
            return ApiException(apiMessage)
        }

        private const val REDIRECT_RESPONSE_EXCEPTION = "Redirect response error"
        private const val CLIENT_REQUEST_EXCEPTION = "Client request error"
        private const val SERVER_RESPONSE_EXCEPTION = "Server response error"
        private const val UNKNOWN_EXCEPTION = "Unknown error"
    }
}