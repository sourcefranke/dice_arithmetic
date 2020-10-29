package com.github.sourcefranke.dicearithmetic.web

import com.fasterxml.jackson.databind.SerializationFeature
import com.github.sourcefranke.dicearithmetic.core.rollDice
import io.ktor.server.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.server.engine.*

fun Application.mainModule() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Routing) {
        route("api") {
            route("roll") {
                get("{formula}") {
                    val formula = call.parameters["formula"].toString()
                    val result = rollDice(formula)
                    call.respond(result)
                }

                get("{times}/{formula}") {
                    val formula = call.parameters["formula"].toString()
                    val times = call.parameters["times"]!!.toInt()
                    val result = rollDice(formula, times)

                    call.respond(result)
                }
            }
        }
    }
}

fun main() {
    embeddedServer(Netty, 8080, module = Application::mainModule).start()
}
