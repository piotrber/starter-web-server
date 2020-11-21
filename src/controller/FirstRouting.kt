package pl.pjpsoft.controller

import io.ktor.application.*
import io.ktor.mustache.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.firstRouting() {
    get("/") {
        call.respond(
            MustacheContent("start.hbs",null))
    }
}
