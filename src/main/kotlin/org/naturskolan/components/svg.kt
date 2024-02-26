package org.naturskolan.components

import kotlinx.html.*

class PATH(consumer: TagConsumer<*>) : HTMLTag("path", consumer, emptyMap(), inlineTag = false, emptyTag = true),
    HtmlInlineTag {
    var d: String by attributes
}

fun SVG.path(block: PATH.() -> Unit = {}) {
    PATH(consumer).visit(block)
}