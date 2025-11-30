package com.muggle

import com.muggle.login.loginRouter
import io.ktor.server.application.*

fun Application.dispatchRouting() {
    defaultRouting()
    loginRouter()
}
