package org.naturskolan.components

import kotlinx.html.*
import java.util.*

fun FlowContent.timePickerIcon(): Unit =
    div(classes = "rounded-full hover:bg-neutral-200 p-1") {
        svg(classes = "w-5 h-5") {
            attributes["fill"] = "currentColor"
            attributes["viewBox"] = "0 0 512 512"
            path {
                d = "M464 256A208 208 0 1 1 48 256a208 208 0 1 1 416 0zM0 256a256 256 0 1 0 512 0A256 256 0 1 0 0 256zM232 120V256c0 8 4 15.5 10.7 20l96 64c11 7.4 25.9 4.4 33.3-6.7s4.4-25.9-6.7-33.3L280 243.2V120c0-13.3-10.7-24-24-24s-24 10.7-24 24z"
            }
        }
    }

inline fun FlowContent.timePicker(label: String = "Välj tid", crossinline block: DIV.() -> Unit = {}): Unit {
    val id = UUID.randomUUID().toString()
    val hours = (1..24).map { i -> i.toString().padStart(2, '0') }
    val minutes = (0 until 6).map { i -> (i * 10).toString().padStart(2, '0') }
    field(id = id, label = label) {
        attributes["x-data"] = """{
            |   close(focusAfter) {
            |       if ($refs.dropdown.open) {
            |           $refs.dropdown.close()
            |       }
            |       focusAfter && focusAfter.focus()
            |   },
            |   toggle() {
            |       if ($refs.dropdown.open) {
            |           this.close($refs.input)
            |       } else {
            |           $refs.dropdown.show()
            |       }
            |   }
            |}""".trimMargin()
        attributes["x-on:keydown.escape"] = "$refs.dropdown.close()"
        attributes["x-on:click.away"] = "$refs.dropdown.close()"
        fieldInput(id = id, type = InputType.text) {
            placeholder = "hh:mm"
            attributes["x-ref"] = "input"
            attributes["x-on:click"] = "close()"
        }
        div(classes = "absolute top-0 bottom-0 right-0 flex items-center px-3 cursor-pointer text-neutral-400 group-hover:text-neutral-500") {
            attributes["x-on:click"] = "toggle()"
            timePickerIcon()
        }
        dialog {
            classes = setOf(
                "absolute",
                "antialiased",
                "bg-white",
                "border",
                "border-neutral-200/70",
                "mt-1",
                "ml-0",
                "py-4",
                "rounded-lg",
                "shadow",
                "z-10"
            )
            attributes["x-ref"] = "dropdown"
            attributes["x-transition.origin.top.left"]
            div(classes = "flex h-24 w-32") {
                ul(classes = "flex-1 overflow-auto group") {
                    attributes["autofocus"] = ""
                    tabIndex = "-1"
                    for ((index, hour) in hours.withIndex()) {
                        li {
                            attributes["aria-role"] = "option"
                            attributes["aria-label"] = hour
                            classes = setOf(
                                "text-center",
                                "transition-colors",
                                "duration-200",
                                "hover:bg-neutral-100",
                                "cursor-pointer"
                            )
                            tabIndex = if (index == 0) "0" else "-1"
                            +hour
                        }
                    }
                }
                ul(classes = "flex-1 overflow-auto group") {
                    attributes["autofocus"] = ""
                    tabIndex = "-1"
                    for ((index, minute) in minutes.withIndex()) {
                        li {
                            attributes["aria-role"] = "option"
                            attributes["aria-label"] = minute
                            classes = setOf(
                                "text-center",
                                "transition-colors",
                                "duration-200",
                                "hover:bg-neutral-100",
                                "cursor-pointer"
                            )
                            tabIndex = if (index == 0) "0" else "-1"
                            +minute
                        }
                    }
                }
            }
        }
    }
}