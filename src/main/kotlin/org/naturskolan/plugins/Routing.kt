package org.naturskolan.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.css.Display
import kotlinx.css.display
import kotlinx.css.fontFamily

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        staticResources("/static", "static")
        get("/styles.css") {
            call.respondCss {
                rule(":host, html") {
                    fontFamily =
                        "Inter,ui-sans-serif,system-ui,-apple-system,Segoe UI,Roboto,Helvetica Neue,Arial,Noto Sans,sans-serif,Apple Color Emoji,Segoe UI Emoji,Segoe UI Symbol,Noto Color Emoji;"
                }
                rule("[un-cloak]") {
                    display = Display.none
                }
                rule(".datepicker-view.flex.grid") {
                    display = Display.grid
                }
            }
        }
    }
}
