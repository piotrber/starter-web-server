package pl.pjpsoft.controller

import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.pjpsoft.data.getPersonList


fun Routing.mainRouting() {
    get("/") {

        setupPersonList(call)
    }
}

suspend fun setupPersonList(call: ApplicationCall){

    val personList = getPersonList()

    call.respond(
        MustacheContent(
            "index.hbs",
            mapOf("personList" to personList))
    )

}