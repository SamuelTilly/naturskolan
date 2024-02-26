package org.naturskolan.booking

import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentDateTime
import org.jetbrains.exposed.sql.kotlin.datetime.datetime
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
data class ExposedBooking(val startTime: LocalDateTime, val endTime: LocalDateTime)

object Booking : Table() {
    val id = integer("id").autoIncrement()
    val startTime = datetime("start_time").defaultExpression(CurrentDateTime)
    val endTime = datetime("end_time")
//    val claimedBy = (integer("user_id") references Users.id).nullable()

    override val primaryKey = PrimaryKey(id)
}

class BookingService(private val database: Database) {
    init {
        transaction(database) { SchemaUtils.create(Booking) }
    }

    private suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun create(booking: ExposedBooking): Int = dbQuery {
        Booking.insert {
            it[startTime] = booking.startTime
            it[endTime] = booking.endTime
        }[Booking.id]
    }

    suspend fun read(id: Int): ExposedBooking? {
        return dbQuery {
            Booking.select { Booking.id eq id }
                .map { ExposedBooking(it[Booking.startTime], it[Booking.endTime]) }
                .singleOrNull()
        }
    }

    suspend fun readAll(): List<ExposedBooking> {
        return dbQuery { Booking.selectAll().map { ExposedBooking(it[Booking.startTime], it[Booking.endTime]) } }
    }

    suspend fun update(id: Int, booking: ExposedBooking) {
        dbQuery {
            Booking.update({ Booking.id eq id }) {
                it[startTime] = booking.startTime
                it[endTime] = booking.endTime
            }
        }
    }

//    suspend fun delete(id: Int) {
//        dbQuery { Booking.deleteWhere { Booking.id.eq(id) } }
//    }
}
