package org.naturskolan.templates

import io.ktor.server.html.*
import kotlinx.html.*

fun UL.menuItem(to: String, highlighted: Boolean? = false, block: A.() -> Unit) {
    li(classes = "inline-block py-6") {
        a(
            classes = "px-2 py-6 border-green-100 " +
                    (if (highlighted == true)
                        "bg-green-900 hover:bg-green-900 text-white font-bold border-b-4"
                    else
                        "text-white hover:text-green-100 hover:border-b-4"), href = to
        ) {
            block()
        }
    }
}

fun FlowContent.menu(): Unit = div {
    classes = setOf("mx-auto w-full max-w-screen-xl shadow-xl bg-green-600 rounded-xl sticky -mt-10")
    nav(classes = "max-w-screen-lg m-auto") {
        ul {
            menuItem(to = "/") { +"Start" }
            menuItem(to = "/om") { +"Om" }
            menuItem(to = "/kontakt") { +"Kontakt" }
            menuItem(to = "/bokning") { +"Bokning" }
            menuItem(to = "/api/auth", highlighted = true) { +"Logga in" }
        }
    }
}

class LayoutTemplate : Template<HTML> {
    val header = Placeholder<FlowContent>()
    val content = Placeholder<FlowContent>()

    override fun HTML.apply() {
        lang = "sv"
        head {
            title { +"Naturskolan" }
            meta { charset = "UTF-8" }
            meta {
                name = "viewport"
                content = "width=device-width, initial-scale=1"
            }
            meta {
                name = "description"
                content = "Naturskolan kvarnhagstorp"
            }
            link {
                rel = "icon"
                href = "/static/favicon.svg"
                type = "image/svg+xml"
            }
            script(src = "/static/unocss.config.js") {}
            script(src = "/static/datepicker.js") {}
            script(src = "//cdn.jsdelivr.net/npm/@unocss/runtime") {}
            script(src = "//cdn.jsdelivr.net/npm/@unocss/runtime/uno.global.js") {} // Preset
            script(src = "//cdn.jsdelivr.net/npm/@alpinejs/focus@3.x.x/dist/cdn.min.js") { defer = true }
            script(src = "//cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js") { defer = true }

            link(rel = "preconnect", href = "https://fonts.googleapis.com")
            link(rel = "preconnect", href = "https://fonts.gstatic.com") { attributes["crossorigin"] = "" }
            link(
                href = "https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700;800&display=swap",
                rel = "stylesheet"
            )
            link(
                rel = "stylesheet",
                href = "https://cdn.jsdelivr.net/npm/@unocss/reset/tailwind.min.css",
                type = "text/css"
            )
            link(rel = "stylesheet", href = "/styles.css", type = "text/css")
        }
        body(classes = "antialiased") {
            attributes["un-cloak"] = "true"

            div(classes = "flex flex-col min-h-screen") {
                header(classes = "bg-green-600 text-white bg-gradient-to-b from-teal-400 to-yellow-200 bg-bottom bg-cover relative pb-20 pt-36 border-b-2 shadow-lg") {
                    img(src = "/static/logo.svg", classes = "w-72 mx-auto p-10 rounded-xl") {
                        attributes["alt"] = "Naturskolan logo"
                    }
                }
                menu()
                main(classes = "py-10 px-4 flex-1") {
                    div(classes = "mx-auto max-w-screen-lg") {
                        h1(classes = "font-semibold text-2xl mb-4") {
                            insert(header)
                        }
                        insert(content)
                    }
                }
                footer(classes = "bg-green-600 text-white py-10")
            }
            iframe {
                hidden = true
                name = "htmz"
                onLoad =
                    "setTimeout(()=>document.querySelector(contentWindow.location.hash||null)?.replaceWith(...contentDocument.body.childNodes))"
            }
        }
    }
}
