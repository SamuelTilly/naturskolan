package org.naturskolan

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.naturskolan.booking.bookingRoutes
import org.naturskolan.plugins.*
import org.naturskolan.user.userRoutes

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val database =
        Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user = "naturskolan",
            driver = "org.h2.Driver",
            password = ""
        )

    configureHTTP()
    configureSerialization()

    configureTemplating()
    configureSecurity()
    configureRouting()

    userRoutes(database)
    bookingRoutes(database)
}
