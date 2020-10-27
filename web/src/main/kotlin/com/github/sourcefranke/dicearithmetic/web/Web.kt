package com.github.sourcefranke.dicearithmetic.web

import io.ktor.server.netty.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.server.engine.*

fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText("Hello, test!", ContentType.Text.Html)
            }
        }
    }.start(wait = true)
}
