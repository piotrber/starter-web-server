package pl.pjpsoft.engine

import pl.pjpsoft.data.insertNewPerson
import pl.pjpsoft.model.Person

fun getPerson(data: MutableMap<String, String>): Person {

    val lname = data.get("lname").toString()
    val fname = data.get("fname").toString()

    var person = Person(0, fname, lname)

    insertNewPerson(person)


    return person
}