/*  using DAO variant */

package pl.pjpsoft.data

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import pl.pjpsoft.model.Person


class PersonDataEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PersonDataEntity>(PersonData)

    var fname by PersonData.fname
    var lname by PersonData.lname
}


fun insertNewSinglePerson(person: Person) {
    val db = DbConnection.db

    transaction {
        val personData = PersonDataEntity.new {

            lname = person.lname
            fname = person.fname
        }
    }

}

//
//fun getPersonList(): List<Person> {
//
//    val db = DbConnection.db
//    val personList = mutableListOf<Person>()
//
//
//    transaction {
//
//        val personDataList = PersonsData.selectAll().toList()
//
//        personDataList.forEach {
//
//            val person = Person(it.id.value, it.fname, it.fname)
//            personList.add(person)
//        }
//    }
//    return personList
//}
//
