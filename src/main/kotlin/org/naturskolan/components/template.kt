package org.naturskolan.components

import kotlinx.html.*

class TEMPLATE(consumer: TagConsumer<*>) :
    HTMLTag("template", consumer, emptyMap(), inlineTag = false, emptyTag = false),
    HtmlBlockTag {
}

fun FlowContent.template(block: TEMPLATE.() -> Unit) {
    TEMPLATE(consumer).visit(block)
}