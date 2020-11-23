package pl.pjpsoft.model

data class Person(val id: Int, val fname: String, val lname:String)
data class PersonList(val persons:List<Person>){
    val person = persons
}