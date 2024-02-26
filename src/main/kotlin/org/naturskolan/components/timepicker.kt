package org.naturskolan.components

import kotlinx.html.*
import java.util.*

inline fun FlowContent.timepicker(label: String = "Välj tid", crossinline block: DIV.() -> Unit = {}): Unit {
    val id = UUID.randomUUID().toString()
    field(id = id, label = label) {
        attributes["x-data"] = """{
            |   open: false,
            |   hours: [...Array(24)].map((_, i) => ("0" + (i + 1)).slice(-2)),
            |   minutes: [...Array(6)].map((_, i) => ("0" + (i * 10)).slice(-2))
            |}""".trimMargin()
        attributes["x-on:keydown.escape"] = "$refs.dropdown.close()"
        attributes["x-on:click.away"] = "$refs.dropdown.close()"
        fieldInput(type = InputType.text) {
            placeholder = "08:00"
            attributes["x-on:click"] = """
                if (open) {
                    $refs.dropdown.close()
                } else {
                    $refs.dropdown.show()
                    open = true
                }
            """.trimIndent()
            attributes["popovertarget"] = "mypopover"
//            span(classes = "flex items-center") {
//                +"Välj tid"
//
//            }
        }
        div(classes = "absolute top-0 right-0 px-3 py-2") {
            svg(classes = "w-2.5 h-2.5 ms-3") {
                attributes["aria-hidden"] = "true"
                attributes["fill"] = "none"
                attributes["viewBox"] = "0 0 10 6"
                path {
                    attributes["stroke"] = "currentColor"
                    attributes["stroke-linecap"] = "round"
                    attributes["stroke-linejoin"] = "round"
                    attributes["stroke-width"] = "2"
                    d = "m1 1 4 4 4-4"
                }
            }
        }
        dialog {
            classes = setOf(
                "absolute",
                "antialiased",
                "bg-white",
                "border",
                "border-neutral-200/70",
                "max-w-lg",
                "mt-1",
                "p-4",
                "rounded-lg",
                "shadow",
                "z-10"
            )
            attributes["x-ref"] = "dropdown"
            attributes["x-on:close"] = "open = false"
            attributes["x-trap"] = "open"
            div(classes = "flex h-24 w-32") {
                div(classes = "flex-1 overflow-auto group") {
                    tabIndex = "0"
                    attributes["autofocus"] = ""
                    template {
                        attributes["x-for"] = "hour in hours"
                        div {
                            attributes["aria-role"] = "option"
                            classes = setOf(
                                "text-center",
                                "transition-colors",
                                "duration-200",
                                "hover:bg-neutral-100",
                            )
                            attributes["x-text"] = "hour"
                        }
                    }
                }
                div(classes = "flex-1 overflow-auto") {
                    tabIndex = "0"
                    template {
                        attributes["x-for"] = "minute in minutes"
                        div(classes = "text-center") {
                            attributes["x-text"] = "minute"
                        }
                    }
                }
            }
        }
    }
}