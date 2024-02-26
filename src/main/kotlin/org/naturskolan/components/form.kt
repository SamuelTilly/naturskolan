package org.naturskolan.components

import kotlinx.html.*

inline fun FlowContent.fieldInput(type: InputType, crossinline block: INPUT.() -> Unit = {}) {
    input(type = type) {
        classes = setOf(
            "flex",
            "w-full",
            "h-10",
            "px-3",
            "py-2",
            "text-sm",
            "bg-white",
            "border",
            "rounded-md",
            "text-neutral-600",
            "border-neutral-300",
            "ring-offset-background",
            "placeholder:text-neutral-400",
            "focus:border-neutral-300",
            "focus:outline-none",
            "focus:ring-2",
            "focus:ring-offset-2",
            "focus:ring-neutral-400",
            "disabled:cursor-not-allowed",
            "disabled:opacity-50"
        )
        block()
    }
}

inline fun FlowContent.field(id: String, label: String, crossinline block: DIV.() -> Unit = {}) {
    div(classes = "w-full mb-4 group") {
        label(classes = "block mb-1 text-sm font-medium text-neutral-500") {
            attributes["for"] = id
            +label
        }
        div(classes = "relative w-[17rem]") {
            block()
        }
    }
}