package pl.pjpsoft.controller

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.pjpsoft.engine.getPerson


fun Routing.newPerson() {
    get("/new") {

        call.respond(
            MustacheContent("form.html", null)
        )

    }
}



fun Routing.savePerson() {

    post("/save") {

        val multipart = call.receiveMultipart()
        val data = mutableMapOf<String, String>()

        multipart.forEachPart { part ->
            if (part is PartData.FormItem) data.put(part.name.toString(), part.value)
            part.dispose()
        }
        call.respond(MustacheContent("accept.hbs", getPerson(data)))

    }

}