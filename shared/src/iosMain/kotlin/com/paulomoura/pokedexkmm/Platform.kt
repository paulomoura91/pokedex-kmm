package com.paulomoura.pokedexkmm

import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

actual fun formatString(format: String, vararg args: Any?): String {
    var formattedString = ""
    val regEx = "%[\\d|.]*[sdf]|%".toRegex()
    val singleFormats = regEx.findAll(format).map {
        it.groupValues.first()
    }.toList()
    val newStrings = format.split(regEx)
    for (i in 0 until args.count()) {
        val arg = args[i]
        formattedString += when (arg) {
            is Double -> {
                NSString.stringWithFormat(newStrings[i] + singleFormats[i], args[i] as Double)
            }
            is Int -> {
                NSString.stringWithFormat(newStrings[i] + singleFormats[i], args[i] as Int)
            }
            else -> {
                NSString.stringWithFormat(newStrings[i] + "%@", args[i])
            }
        }
    }
    return formattedString
}