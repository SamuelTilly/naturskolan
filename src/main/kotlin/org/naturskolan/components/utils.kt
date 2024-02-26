package org.naturskolan.components

const val el = "\$el"
const val refs = "\$refs"

fun <T> T.toAttributeString(): String {
    return when (this != null) {
        true -> "\"$this\""
        false -> "null"
    }
}