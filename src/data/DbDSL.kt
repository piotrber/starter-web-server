package pl.pjpsoft.data

/*  using DSL variant */

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import pl.pjpsoft.model.Person
import pl.pjpsoft.model.PersonList

object DbConnection {

    val db by lazy {
        Database.connect("jdbc:sqlite:file:data/data.db", "org.sqlite.JDBC")
    }

}

val db = DbConnection.db

object PersonData : IntIdTable() {
    val fname = varchar("fname", 50)
    val lname = varchar("lname", 50)
}

fun getSinglePerson(personId: Int): Person {

    lateinit var person: Person

    transaction {

        val personData = PersonData.select { (PersonData.id eq personId) }
        personData.forEach { person = mapResultRowToPerson(it) }
    }

    return person
}

fun updatePerson(person: Person) {

    transaction {

        PersonData.update({ PersonData.id eq person.id }) {
            it[fname] = person.fname
            it[lname] = person.lname
        }
    }

}


fun insertNewPerson(person: Person) {

    transaction {

        val lastId = PersonData.insert {

            it[fname] = person.fname
            it[lname] = person.lname

        } get PersonData.id

    }

}

fun deletePerson(personId: Int) {

    transaction {
        PersonData.deleteWhere { PersonData.id eq personId }
    }

}


fun getPersonList(): PersonList {

    val personList = personDataList()
    return PersonList(personList)

}

fun personDataList():List<Person>{

    val personList = mutableListOf<Person>()
    transaction {
        val personData = PersonData.selectAll().toList()

        personData.forEach {
            val person = mapResultRowToPerson(it)
            personList.add(person)
        }
    }
    return personList
}


fun execRawSql(sql:String){

    transaction{
        exec(sql)
    }
}

fun mapResultRowToPerson(it: ResultRow): Person =
    Person(id = it[PersonData.id].value, fname = it[PersonData.fname], lname = it[PersonData.lname])
