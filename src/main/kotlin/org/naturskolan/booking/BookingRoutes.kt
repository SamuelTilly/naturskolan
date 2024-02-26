package org.naturskolan.booking

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.div
import kotlinx.html.label
import org.jetbrains.exposed.sql.Database
import org.naturskolan.components.datePicker
import org.naturskolan.components.primaryButton
import org.naturskolan.components.secondaryButton
import org.naturskolan.components.timePicker
import org.naturskolan.templates.LayoutTemplate

fun Application.bookingRoutes(database: Database) {
    val bookingService = BookingService(database)

    routing {
        get("/booking") {
            val books = bookingService.readAll()
            call.respondHtmlTemplate(LayoutTemplate()) {
                header { +"Lägg till bokningsbara datum" }
                content {
                    div(classes = "mb-5 flex gap-4 items-center") {
                        datePicker(label = "Välj startdatum")
//                        datePicker(id = "from", label = "Startar", placeholder = "Välj datum")
                        timePicker(label = "Välj starttid")
                    }
                    div(classes = "mb-5 flex gap-4 items-center") {
                        datePicker(label = "Välj slutdatum")
//                        datePicker(id = "to", label = "Slutar", placeholder = "Välj datum")
                        timePicker(label = "Välj sluttid")
                    }
                    div(classes = "mb-5 flex gap-4 items-center") {
                        label(classes = "block text-sm font-medium text-gray-900 dark:text-white") {
                            +"Upprepa"
                        }
                        primaryButton() { +"dropdown" }
                    }
                    div(classes = "flex gap-4") {
                        secondaryButton() { +"Avbryt" }
                        primaryButton() { +"Lägg till" }
                    }
                    for (book in books) {
                        div { +"Booking: ${book.startTime} - ${book.endTime}" }
                    }
                }
            }
        }
        post("/api/booking") {
            val booking = call.receive<ExposedBooking>()

            if (booking.startTime > booking.endTime) {
                call.respond(HttpStatusCode.BadRequest, "startTime needs to earlier then endTime")
            }

            val id = bookingService.create(booking)
            call.respond(HttpStatusCode.Created, id)
        }
    }
}