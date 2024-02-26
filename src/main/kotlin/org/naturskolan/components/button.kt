package org.naturskolan.components

import kotlinx.html.BUTTON
import kotlinx.html.FlowContent
import kotlinx.html.button
import kotlinx.html.classes

private var commonButtonStyle = arrayOf(
    "inline-flex",
    "items-center",
    "justify-center",
    "px-4",
    "py-2",
    "text-sm",
    "font-medium",
    "tracking-wide",
    "transition-colors",
    "duration-200",
    "rounded-md",
    "focus:ring-2",
    "focus:ring-offset-2",
    "focus:outline-none",
    "focus:shadow-outline"
)

fun FlowContent.primaryButton(block: BUTTON.() -> Unit): Unit = button {
    classes = setOf(
        *commonButtonStyle,
        "text-white",
        "bg-green-600",
        "hover:bg-green-900",
        "focus:ring-green-900",
    ); block()
}

fun FlowContent.secondaryButton(block: BUTTON.() -> Unit): Unit = button {
    classes = setOf(
        *commonButtonStyle,
        "bg-white",
        "border",
        "text-neutral-500",
        "hover:text-neutral-700",
        "border-neutral-200/70",
        "hover:bg-neutral-100",
        "active:bg-white",
        "focus:bg-white",
        "focus:ring-neutral-200/60",
    ); block()
}