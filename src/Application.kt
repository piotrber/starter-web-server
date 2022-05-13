package pl.pjpsoft

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.mustache.*
import io.ktor.server.plugins.*
import io.ktor.server.routing.*
import pl.pjpsoft.API.routingApi
import pl.pjpsoft.controller.mainRouting
import pl.pjpsoft.controller.personRouting
import pl.pjpsoft.controller.staticRouting

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {


    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates/mustache")
    }

    install(ContentNegotiation){
        json()
    }

    install(DefaultHeaders)

    routing {

        mainRouting()
        personRouting()
        staticRouting()
        routingApi()
    }
}



