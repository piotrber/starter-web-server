package pl.pjpsoft.controller

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.pjpsoft.model.MustacheUser

fun Routing.secondRouting() {

    post("/zapisz") {

        val multipart = call.receiveMultipart()
        val data = mutableMapOf<String,String>()

        multipart.forEachPart { part ->
            if (part is PartData.FormItem) data.put(part.name.toString(),part.value)
            part.dispose()
        }
        call.respondText("OK", ContentType.Text.Plain)

    }


    get("/html-mustache") {
        call.respond(MustacheContent("index.hbs", mapOf("user" to MustacheUser(1, "Piotr", "Berłowski"))))
    }


}