package pl.pjpsoft.data

/*  using DSL variant */

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import pl.pjpsoft.model.Person
import pl.pjpsoft.model.PersonList

object DbConnection {

    val db by lazy {
        Database.connect("jdbc:sqlite:file:data/data.db", "org.sqlite.JDBC")
    }

}

object PersonData : IntIdTable() {
    val fname = varchar("fname", 50)
    val lname = varchar("lname", 50)
}

fun insertNewPerson(person: Person) {
    val db = DbConnection.db

    transaction {

        val lastId = PersonData.insert {

            it[fname] = person.fname
            it[lname] = person.lname

        } get PersonData.id

    }

}

fun getPersonList(): PersonList {

    val db = DbConnection.db
    val personList = mutableListOf<Person>()

    transaction {

        val personDataList = PersonData.selectAll().toList()

        personDataList.forEach {
            val person = mapResultRowToPerson(it)
            personList.add(person)
        }
    }

    return PersonList(personList)
}


fun mapResultRowToPerson(it: ResultRow):Person =
    Person(id = it[PersonData.id].value, fname = it[PersonData.fname], lname = it[PersonData.lname])
