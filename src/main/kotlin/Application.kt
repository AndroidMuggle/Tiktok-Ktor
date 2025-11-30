package com.muggle

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        entryPoint()
    }.start(wait = true)
}

fun Application.entryPoint() {
    configureRouting()
    dispatchRouting()
}
