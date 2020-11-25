package pl.pjpsoft.controller

import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.mustache.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.pjpsoft.data.getSinglePerson
import pl.pjpsoft.engine.newPersonData
import pl.pjpsoft.engine.personData
import pl.pjpsoft.model.Person


fun Routing.personRouting() {
    newPerson()
    savePerson()
    editPerson()
    updatePerson()
    deletePerson()
}


fun Routing.newPerson() {
    get("/new") {

        call.respond(
            MustacheContent("form.html", null)
        )

    }
}

fun Routing.editPerson() {
    get("/edit") {


        val personId = call.request.queryParameters["id"]?.toInt()
        if (personId != null) {
            val person = getSinglePerson(personId)
            call.respond(
                MustacheContent("person.hbs", mapOf("person" to person))
            )
        }
    }
}


fun Routing.deletePerson() {
    get("/delete") {

        val personId = call.request.queryParameters["id"]?.toInt()
        if (personId != null) pl.pjpsoft.data.deletePerson(personId)
        setupPersonList(call)
    }
}


fun Routing.updatePerson() {

    post("/update") {

        val multipart = call.receiveMultipart()
        insertOrUpdatePerson(multipart, call, ::personData)
    }

}

fun Routing.savePerson() {

    post("/save") {

        val multipart = call.receiveMultipart()
        insertOrUpdatePerson(multipart, call, ::newPersonData)
    }

}

suspend fun insertOrUpdatePerson(
    multipart: MultiPartData,
    call: ApplicationCall,
    execFun: (data: MutableMap<String, String>) -> Person
) {

    val data = mutableMapOf<String, String>()
    multipart.forEachPart { part ->
        if (part is PartData.FormItem) data.put(part.name.toString(), part.value)
        part.dispose()
    }
    call.respond(MustacheContent("accept.hbs", execFun(data)))
}