package pl.pjpsoft.engine

import pl.pjpsoft.data.insertNewPerson
import pl.pjpsoft.data.updatePerson
import pl.pjpsoft.model.Person

fun newPersonData(data: MutableMap<String, String>): Person {

    val lname = data.get("lname").toString()
    val fname = data.get("fname").toString()

    var person = Person(0, fname, lname)
    insertNewPerson(person)
    return person
}


fun personData(data: MutableMap<String, String>): Person {

    val lname = data.get("lname").toString()
    val fname = data.get("fname").toString()
    val id = data.get("id")?.toInt()

    lateinit var person: Person

    if (id != null) {
        person = Person(id, fname, lname)
        updatePerson(person)
    } else {
        person = Person(0, "", "")
    }
    return person
}
