package com.paulomoura.pokedexkmm.data.remote

import co.touchlab.kermit.Logger
import com.paulomoura.pokedexkmm.common.constants.LogTags

inline fun logHttpClientMessage(message: String) = Logger.i(tag = LogTags.KTOR) { message }