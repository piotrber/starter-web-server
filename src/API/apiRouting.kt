package pl.pjpsoft.API

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.pjpsoft.data.getSinglePerson
import pl.pjpsoft.data.personDataList
import pl.pjpsoft.model.Person


fun Routing.routingApi() {
    getPersonData()
    getAllPersonData()
    savePersonData()
}


fun Routing.getPersonData() {

    get("/singlePerson") {

        val personId = call.request.queryParameters["id"]?.toInt()
        if (personId != null) {
            val person = getSinglePerson(personId)
            call.respond(
                mapOf("person" to person)
            )

        }
    }
}


fun Routing.getAllPersonData() {

    get("/all") {

        val personList = personDataList()

        call.respond(
            mapOf("personList" to personList)
        )
    }
}

fun Routing.savePersonData() {

    post("/savedata") {
        val person = withContext(Dispatchers.IO) {
            call.receive<Person>()
        }
        call.respond(HttpStatusCode.OK)
    }
}
