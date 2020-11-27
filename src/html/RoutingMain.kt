package pl.pjpsoft.controller

import io.ktor.application.*
import io.ktor.mustache.*
import io.ktor.response.*
import io.ktor.routing.*
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