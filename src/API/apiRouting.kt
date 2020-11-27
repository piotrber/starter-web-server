package pl.pjpsoft.API

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import pl.pjpsoft.data.getSinglePerson
import pl.pjpsoft.data.personDataList


fun Routing.routingApi() {
    getPersonData()
    getAllPersonData()
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
